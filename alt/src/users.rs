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

        pub fn new(username: &str, email: &str, account_password: &str) -> User {
            // can be improved to be lazy/constants
            let name_format: Regex = Regex::new(r"^[a-zA-Z\d][a-zA-Z\d ]*?[a-zA-Z\d]$").unwrap();
            let mail_format: Regex = Regex::new(r"^([a-z0-9_+]([a-z0-9_+.]*[a-z0-9_+])?)@([a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6})").unwrap();

            // check formats
            assert!(name_format.is_match(username));
            assert!(mail_format.is_match(email));

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

            return user;
        }

        pub fn update(id: &str, username: &str, email: &str, billing_address: &str, postal_code: &str) {
            // can be improved to be lazy/constants
            let name_format: Regex = Regex::new(r"^[a-zA-Z\d][a-zA-Z\d ]*?[a-zA-Z\d]$").unwrap();
            let mail_format: Regex = Regex::new(r"^([a-z0-9_+]([a-z0-9_+.]*[a-z0-9_+])?)@([a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6})").unwrap();
            let zip_format: Regex = Regex::new(r"^[ABCEGHJ-NPRSTVXY]\d[ABCEGHJ-NPRSTV-Z][ -]?\d[ABCEGHJ-NPRSTV-Z]\d$").unwrap();

            assert!(name_format.is_match(username));
            assert!(mail_format.is_match(email));
            assert!(zip_format.is_match(postal_code));

            let db: Dao = Dao::new();

            let replacements = format!("SET name = '{}', address = '{}', zip = '{}'", username, billing_address, postal_code);
            db.replace("users", &replacements, id);

        }
    }

    impl Hash for User {
        fn hash<H: Hasher>(&self, state: &mut H) {
            self.mail.hash(state);
            self.password.hash(state)
        }
    }

}

