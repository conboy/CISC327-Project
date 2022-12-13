pub mod bookings {
    use crate::dao::dao::Dao;

    pub struct Booking {
        pub price: u32,
        pub host_id: u64
    }

    impl Booking {
        pub fn new(price: u32, host_id: u64) -> Booking {
            
            db = Dao::new();

            // TODO: check that id's are in the db
            // TODO: make time work
            // TODO: add to db
            
            booking {
                price: price,
                host_id: host_id,
            }
        }

        pub fn update(new_price: u32, listing_id: u64) -> bool {

            db = Dao::new();

            // TODO: find booking with listing_id
            // TODO: update found booking with new_price & save in db
        }
    }
}
