pub mod listings {
    use crate::dao::dao::Dao;
    use std::hash::Hash;
    use chrono::{DateTime, Utc, Duration, NaiveDate};

    #[derive(Hash)]
    pub struct Listing {
        pub price: u32,
        pub host_id: u64,
        pub name: String,
        pub description: String,
        pub last_edit: DateTime<Utc>,
    }

    impl Listing {
        pub fn new(name: &str, price: u32, host_id: &str, description: &str) -> Option<String> {
            let db = Dao::new();

            let last_edit = Utc::now();

            if !db.find("users", host_id) {
                return None
            } else {
                let listing = Listing {
                    price: price,
                    host_id: host_id.parse::<u64>().unwrap(),
                    name: name.to_string(),
                    description: description.to_string(),
                    last_edit: last_edit,
                };
                db.add(&listing, "listings (id, price, host_id, name, description, last_edit)", 
                       &format!("{},{},'{}','{}','{}'", price, host_id, name, description, last_edit.to_string()));
                let id = format!("{}", Dao::calculate_hash(&listing));
                return Some(id)
            }
        }

        pub fn update(new_name: &str, new_price: u32, new_description: &str, id: &str) -> Option<String> {
            let db = Dao::new();

            println!("{}", id);

            if !db.find("listings", id) {
                return None
            } else {
                let condition = format!("id = {}", id);
                let host_id = db.get::<String>("listings", 2, &condition).unwrap().parse::<u64>().unwrap();
                let last_edit = Utc::now();
                let listing = Listing {
                    price: new_price,
                    host_id: host_id,
                    name: new_name.to_string(),
                    description: new_description.to_string(),
                    last_edit: last_edit,
                };
                let new_id = &Dao::calculate_hash(&listing).to_string()[0..19];
                db.replace("listings", 
                           &format!("id={}, price={}, name='{}',description='{}', last_edit='{}'", 
                                    new_id, new_price, new_name, new_description, last_edit.to_string()), 
                           id);
                return Some(new_id.to_string())
            }
        }
    }
}
