pub mod transactions {
    use std::hash::{Hash, Hasher};
    use chrono::{DateTime, Utc};
    use crate::dao::dao::Dao;

    #[derive(Hash)]
    pub struct Transaction {
        pub date: DateTime<Utc>,
        pub amount: u32,
        host_id: u64,
        guest_id: u64,
    }

    impl Transaction {

        pub fn new(amount: u32, host_id: u64, guest_id: u64) -> Option<String> {

            let db = Dao::new();
            
            if !db.find("users", &host_id.to_string()) || !db.find("users", &guest_id.to_string()) {
                return None
            } else {
                let trans = Transaction {
                    date: Utc::now(),
                    amount: amount,
                    host_id: host_id,
                    guest_id: guest_id,
                };

                db.add(&trans, "transactions (id, date, amount, host_id, guest_id)", 
                       &format!("{},{},{},{}", trans.date, trans.amount, trans.host_id, trans.guest_id));

                return Some(format!("{}", Dao::calculate_hash(&trans)))
            }
        }

    }
}
