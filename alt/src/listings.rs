pub mod listings {
    use crate::dao::dao::Dao;
    use std::hash::Hash;

    #[derive(Hash)]
    pub struct Listing {
        pub price: u32,
        pub host_id: u64,
        pub name: String,
    }

    impl Listing {
        pub fn new(name: &str, price: u32, host_id: &str) -> Option<String> {
            let db = Dao::new();

            if !db.find("users", host_id) {
                return None
            } else {
                let listing = Listing {
                    price: price,
                    host_id: host_id.parse::<u64>().unwrap(),
                    name: name.to_string(),
                };
                db.add(&listing, "listings (id, price, host_id, name)", 
                       &format!("{},{},'{}'", price, host_id, name));
                let id = format!("{}", Dao::calculate_hash(&listing));
                return Some(id)
            }
        }

        pub fn update(new_name: &str, new_price: u32, listing_id: &str) -> Option<String> {
            let db = Dao::new();

            if !db.find("listings", listing_id) {
                return None
            } else {
                let condition = format!("id = {}", listing_id);
                let host_id = db.get::<String>("listings", 2, &condition).unwrap().parse::<u64>().unwrap();
                let listing = Listing {
                    price: new_price,
                    host_id: host_id,
                    name: new_name.to_string()
                };
                db.add(&listing, "listings (id, price, host_id, name)", 
                       &format!("{},{},'{}'", new_price, host_id, new_name));
                let id = format!("{}", Dao::calculate_hash(&listing));
                return Some(id)
            }
        }
    }
}
