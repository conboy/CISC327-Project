pub mod dao {
    use std::collections::hash_map::DefaultHasher;
    use std::hash::{Hash, Hasher};
    use sqlite::{Error, State};
    use std::str::FromStr;
    use std::fmt::Display;


    pub struct Dao {
        file_name: String,
    }

    impl Dao {

        pub fn new() -> Dao {
            Dao {
                file_name: "./data/qbnb.db".to_string(),
            }
        }
        
        pub fn add<T: Hash>(&self,object: &T, table: &str, data: &str) {
            // connect to db
            let connection = sqlite::open(&self.file_name).unwrap();
            // construct query to add to db
            let id = Self::calculate_hash(object).to_string();
            let data_with_id = format!("{},{}", id, data);
            let query = format!("INSERT INTO {} VALUES ({});", table, data_with_id);
            // execute query
            connection.execute(&query).unwrap();

            println!("[sql] {}", query);
        }

        pub fn get<T: sqlite::ReadableWithIndex>(&self, table: &str, index: usize, conditions: &str) -> Result<T, sqlite::Error> {
            let connection = sqlite::open(&self.file_name).unwrap();
            let query = format!("SELECT * FROM {} WHERE {};", table, conditions);
            let mut request = connection.prepare(&query)?;

            if let Ok(State::Row) = request.next() {
                request.read::<T, _>(index)
            } else {
                Err(sqlite::Error{code: None, message: Some("id is not present in db".to_string())})
            }
        }

        pub fn find(&self, table: &str, id: &str) -> bool {

            let condition = format!("id = {}", id);
            let item = self.get::<String>(table, 0, &condition);

            if let Ok(String) = item {
                true
            } else {
                false
            }
        }

        pub fn replace(&self, table: &str, replacements: &str, id: &str) {
            let connection = sqlite::open(&self.file_name).unwrap();

            let query = format!("UPDATE {} SET {} WHERE id = {};", table, replacements, id);
            connection.execute(&query).unwrap();
            println!("[sql] {}", query);
        }

        pub fn get_all<T: sqlite::ReadableWithIndex>(&self, table: &str, index: usize) -> Result<T, sqlite::Error> {
            let connection = sqlite::open(&self.file_name).unwrap();
            let query = format!("SELECT * FROM {};", table);
            let mut request = connection.prepare(&query)?;

            let output = Vec<T>

            while let Ok(State::Row) = request.next() {
                let value = request.read::<T, _>(index);
                output.append(value);
            }         
            return output
        }

        pub fn calculate_hash<T: Hash>(object: &T) -> u64 {
            let mut hasher = DefaultHasher::new();
            object.hash(&mut hasher);
            hasher.finish()
        }
    }
}
