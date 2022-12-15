pub mod transactions {
    use std::hash::{Hash, Hasher};
    use chrono::{DateTime, Utc, Duration, NaiveDate};
    use crate::dao::dao::Dao;

    #[derive(Hash)]
    pub struct Transaction {
        pub listing_id: u64,
        pub start: String,
        pub days: i64,
        pub guest_id: u64,
    }

    impl Transaction {
        pub fn new(start: &str, days: i64, guest_id: &str, listing_id: &str) -> Option<String> {
            let db = Dao::new();
            
            let trans = Transaction {
                listing_id: listing_id.parse::<u64>().unwrap(),
                start: start.to_string(),
                days: days,
                guest_id: guest_id.parse::<u64>().unwrap(),
            };
            let id = Dao::calculate_hash(&trans).to_string();


            let start_date: DateTime<Utc> = Self::convert_to_utc(start);
            let days_length: Duration = Duration::days(days);

            if !db.find("listings", listing_id) || !db.find("users", guest_id) || db.find("transactions", &id) || !Self::dates_available(start_date, days_length, listing_id){
                return None
            } else {
                let host_id = db.get::<String>("listings", 2, listing_id).unwrap();
                if host_id == guest_id {
                    return None
                }

                db.add(&trans, "transactions (id, start, days, listing_id, guest_id)", 
                       &format!("'{}',{},{},{}", start, days, listing_id, guest_id));
                return Some(id)
            }
        }


        fn dates_available(start: DateTime<Utc>, length: Duration, listing_id: &str) -> bool {
            let db = Dao::new();

            if !db.find("listings", listing_id) {
                return false
            }

            let condition = format!("WHERE listing_id = {}", listing_id);
            let starts = db.get_all::<String>("transactions", 1, &condition);
            let lengths = db.get_all::<i64>("transactions", 2, &condition);

            let starts_vec = starts.unwrap();
            let lengths_vec = lengths.unwrap();

            for i in 0..starts_vec.len() {
                let check_start = Self::convert_to_utc(&starts_vec[i]);
                let check_length = Duration::days(lengths_vec[i]);


                if (check_start <= start && start < check_start + check_length) || 
                   (check_start <= start + length && start + length < check_start + check_length) {
                    return false
                }
            }
            return true
        }

        fn convert_to_utc(date: &str) -> DateTime<Utc> {
                let niave_date = NaiveDate::parse_from_str(date, "%Y-%m-%d").unwrap().and_hms_opt(0,0,0).unwrap();
                DateTime::<Utc>::from_utc(niave_date, Utc)
        }
    }
}
