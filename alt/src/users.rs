pub mod users {
    use regex::Regex;
    use std::hash::{Hash, Hasher};
    use crate::dao::dao::Dao;

    pub struct User {
        pub name: String,
        pub mail: String,
        password: String,
        pub address: Option<String>,
        pub zip: Option<String>,
    }

    impl User {

        pub fn new(username: &str, email: &str, account_password: &str) -> Option<String> {
            // can be improved to be lazy/constants
            let name_format: Regex = Regex::new(r"^[a-zA-Z\d][a-zA-Z\d ]*?[a-zA-Z\d]$").unwrap();
            let mail_format: Regex = Regex::new(r"^([a-z0-9_+]([a-z0-9_+.]*[a-z0-9_+])?)@([a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6})").unwrap();

            // check formats
            if !name_format.is_match(username) || !mail_format.is_match(email) {
                return None
            }

            // create user
            let user = User {
                name: username.to_string(),
                mail: email.to_string(),
                password: account_password.to_string(),
                address: None,
                zip: None,
            };

            // save new user to db
            let db = Dao::new();
            db.add(&user, "USERS (id, name, mail, password)", 
                   &format!("'{}','{}','{}'", username, email, account_password));

            let id = format!("{}", Dao::calculate_hash(&user));

            return Some(id)
        }

        pub fn update(id: &str, username: &str, email: &str, billing_address: &str, postal_code: &str) -> Option<String>{
            // can be improved to be lazy/constants
            let name_format: Regex = Regex::new(r"^[a-zA-Z\d][a-zA-Z\d ]*?[a-zA-Z\d]$").unwrap();
            let mail_format: Regex = Regex::new(r"^([a-z0-9_+]([a-z0-9_+.]*[a-z0-9_+])?)@([a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6})").unwrap();
            let zip_format: Regex = Regex::new(r"^[ABCEGHJ-NPRSTVXY]\d[ABCEGHJ-NPRSTV-Z][ -]?\d[ABCEGHJ-NPRSTV-Z]\d$").unwrap();

            // check formats
            if !name_format.is_match(username) || !mail_format.is_match(email) || !zip_format.is_match(postal_code) {
                return None
            }

            let db: Dao = Dao::new();

            // must format in order to make sure value does not have decimal
            let current_id = format!("{}", id);
            let condition = format!("id = {}", current_id);
            let password = db.get::<String>("users", 3, &condition);
            
            if let Ok(ref String) = password {
                // if response is ok, update user & user_id
                let new_id = Dao::calculate_hash(&Self::possible_user(email, &password.unwrap()));
                let replacements = format!(
                    "id = '{}', mail = '{}', name = '{}', address = '{}', zip = '{}'", 
                    new_id, email, username, billing_address, postal_code
                );
                db.replace("users", &replacements, id);
                return Some(format!("{}",new_id))
            } else {
                // if response is not ok, return none
                return None
            }
        }

        // used to create hash when logging in / updating profile
        pub fn possible_user(mail: &str, password: &str) -> User {
            User {
                mail: mail.to_string(),
                password: password.to_string(),
                name: "possible_user".to_string(),
                address: None,
                zip: None,
            }
        }
    }

    impl Hash for User {
        fn hash<H: Hasher>(&self, state: &mut H) {
            self.mail.hash(state);
            self.password.hash(state)
        }
    }
}

