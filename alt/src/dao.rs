pub mod dao {
    use std::collections::hash_map::DefaultHasher;
    use std::hash::{Hash, Hasher};
    use sqlite::Error as sqlErr;


    pub struct Dao {
        file_name: String,
    }

    impl Dao {

        pub fn new() -> Dao {
            Dao {
                file_name: "./data/qbnb.db".to_string(),
            }
        }
        
        pub fn add<T: Hash>(&self,object: &T, data_spec: &str, data: &str) {
            // connect to db
            let connection = sqlite::open(&self.file_name).unwrap();

            // construct query to add to db
            let data_with_id = format!("{},{}", Self::calculate_hash(object), data);
            let query = format!("INSERT INTO {} VALUES ({});", data_spec, data_with_id);

            // execute query
            connection.execute(&query).unwrap();

            println!("[sql] {}", query);
        }

//      pub fn find(&self, data_spec: &str, id: &str) {
//          let connection = sqlite::open(&self.file_name).unwrap();

//          let query = format!("SELECT id FROM {} WHERE id = {};", data_spec, id);

//          connection.execute(&query).unwrap();
//          println!("[sql] {}", query);
//      }

        pub fn replace(&self, data_spec: &str, replacements: &str, id: &str) {
            //let connection = sqlite::open(&self.file_name).unwrap();

            let query = format!("UPDATE {} {} WHERE id = {};", data_spec, replacements, id);
            //connection.execute(&query).unwrap();
            println!("[sql] {}", query);
        }

        pub fn calculate_hash<T: Hash>(object: &T) -> u64 {
            let mut hasher = DefaultHasher::new();
            object.hash(&mut hasher);
            hasher.finish()
        }
    }
}
