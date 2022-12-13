pub mod transactions {
    use std::hash::{Hash, Hasher};
    use chrono::{DateTime, Utc, Duration};
    use crate::dao::dao::Dao;

    #[derive(Hash)]
    pub struct Transaction {
        pub listing_id: u64,
        pub start: DateTime<Utc>,
        pub days: i32
        pub guest_id: u64,
    }

    impl Transaction {
        pub fn new(start: DateTime<Utc>, days: i32, guest_id: &str, listing_id: &str) -> Option<String> {
            let db = Dao::new();
            
            let trans = Transaction {
                listing_id: listing_id.parse::<u64>().unwrap(),
                start: start,
                days: days
                guest_id: guest_id.parse::<u64>().unwrap(),
            };
            let id = Dao::calculate_hash(&trans).to_string();

            if !db.find("listings", listing_id) || !db.find("users", guest_id) || db.find("transactions", id) {
                return None
            } else {
                db.add(&trans, "transactions (id, start, days, listing_id, guest_id)", 
                       &format!("{},{},{},{}", start, days, listing_id, guest_id ));
                return Some(id)
            }
        }

        fn dates_available(start: DateTime<Utc>, length: Duration) -> bool {
            let db = Dao::new();

            let starts = db.get_all::<String>("transactions", 1);
            let lengths = db.get_all::<i32>("transactions", 2);

            // TODO: make sure that given start and length are not within existing date-ranges
            // (starts & lengths). 
            //
            // Note: Uses Chrono for time representation
        }
    }
}
