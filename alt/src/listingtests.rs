#[cfg(test)]
pub mod listingtests {
    use rand::{distributions::Alphanumeric, Rng};
    use crate::users::users::User;
    use crate::listings::listings::Listing;

    fn get_user_id () -> String {
        let user = User::new("FunnyGuy49", "supergamer@wizard.com", "1234GamerGamer!");
        if let Some(ref String) = user {
            user.unwrap().to_string()
        } else {
            panic!("User wasn't loaded");
        }
    }

    //A very basic test to make sure everything is loaded okay
    #[test]
    fn test_test() {
        assert!(true);
    }

    //R1 - testing listing title formatting
    #[test]
    fn R1() {
        let id = "dummy";

        let test = Listing::new("!^$£%@£$*^??><()", "A lovely lovely place indeed!", 100, id);
        assert!(test.is_none());
        let test = Listing::new(" FrontSpace", "A lovely lovely place indeed!", 100, id);
        assert!(test.is_none());
        let test = Listing::new("EndSpace ", "A lovely lovely place indeed!", 100, id);
        assert!(test.is_none());
    }

    //R2 - testing listing title length
    #[test]
    fn R2() {
        let id = "dummy";

        let test = Listing::new("Small", "A lovely lovely place indeed! Too small", 100, id);
        assert!(test.is_none());
        let test = Listing::new("An incredibly long and arduous title that I find frankly too stupid and annoying personally",
                                "An even longer desc An even longer desc An even longer desc An even longer desc An even longer",
                                100, id);
        assert!(test.is_none());
    }

    //R3 - testing listing description length
    #[test]
    fn R3() {
        let id = "dummy";

        let long_desc: String = rand::thread_rng()
            .sample_iter(&Alphanumeric)
            .take(2001)
            .map(char::from)
            .collect();
        println!("{}", long_desc);

        let test = Listing::new("Too small desc", "Too small", 100, id);
        assert!(test.is_none());
        let test = Listing::new("Too long desc", &long_desc, 100, id);
        assert!(test.is_none());
    }

    //R4 - testing that the listing title is shorter than the description
    #[test]
    fn R4() {
        let id = "dummy";

        let test = Listing::new("Funky title four thousand innit", "A pitiful short description", 100, id);
        assert!(test.is_none());
    }

    //R5 - testing price range
    #[test]
    fn R5() {
        let id = "dummy";

        let test = Listing::new("Tiny price", "A pitiful short description", 1, id);
        assert!(test.is_none());
        let test = Listing::new("Jumbo price", "A pitiful short description", 999999, id);
        assert!(test.is_none());
    }

    //can't do R6, R7, or R8 until full implementation
}