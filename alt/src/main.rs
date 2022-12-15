#[macro_use] extern crate rocket;

mod users;
mod dao;
mod cors;
mod transactions;
mod listings;

use std::path::Path;
use rocket::fs::{NamedFile, FileServer, relative};
use rocket::http::{Cookie, CookieJar};
use rocket::serde::json::Json;

use crate::users::users::User;
use crate::dao::dao::Dao;
use crate::cors::cors::CORS;
use crate::transactions::transactions::Transaction;
use crate::listings::listings::Listing;


#[get("/")]
async fn landing() -> Option<NamedFile> {
    NamedFile::open(Path::new("html/register.html")).await.ok()
}

#[post("/register/<mail>/<name>/<password>")]
fn register(mail: &str, name: &str, password: &str, cookies: &CookieJar<'_>) -> String {
    let user_id: Option<String> = User::new(name, mail, password);

    if let Some(ref String) = user_id {
        return user_id.unwrap()
    } else {
        "ERROR".to_string()
    }
}

#[post("/login/<mail>/<password>")]
fn login(mail: &str, password: &str) -> String {
    let db = Dao::new();
    let user = User::possible_user(mail, password);
    let id = format!("{}", Dao::calculate_hash(&user));

    if db.find("users", &id) {
        return id
    } else {
        "ERROR".to_string()
    }
}

#[post("/createListing/<name>/<price>/<description>/<user_id>")]
async fn create_listing(name: &str, price: u32, user_id: &str, description: &str) -> String {
    let listing_id = Listing::new(name, price, user_id, description);

    if let Some(ref String) = listing_id {
        "SUCCESS".to_string()
    } else {
        "ERROR".to_string()
    }
}

#[get("/listings/<id>")]
async fn get_listings(id: &str) -> Json<Vec<String>> {
    let db = Dao::new();

    let condition = format!("WHERE host_id <> {}", id);
    let ids = db.get_all::<String>("listings", 0, &condition);
    let names = db.get_all::<String>("listings", 3, &condition);
    let descriptions = db.get_all::<String>("listings", 4, &condition);

    let id_vec = ids.unwrap();
    let name_vec = names.unwrap();
    let description_vec = descriptions.unwrap();

    let mut output = Vec::new();

    for i in 0..id_vec.len() {
        let combo = format!("id :: {} | {} \n {}", id_vec[i], name_vec[i], description_vec[i]);
        output.push(combo);
    }

    return Json(output)
}

#[get("/userListings/<id>")]
async fn get_user_listings(id: &str) -> Json<Vec<String>> {
    let db = Dao::new();

    let condition = format!("WHERE host_id = {}", id);

    let ids = db.get_all::<String>("listings", 0, &condition);
    let names = db.get_all::<String>("listings", 3, &condition);
    let descriptions = db.get_all::<String>("listings", 4, &condition);

    let id_vec = ids.unwrap();
    let name_vec = names.unwrap();
    let description_vec = descriptions.unwrap();

    let mut output = Vec::new();

    for i in 0..id_vec.len() {
        let combo = format!("id :: {} | {} \n {}", id_vec[i], name_vec[i], description_vec[i]);
        output.push(combo);
    }

    return Json(output)
}

#[get("/userTransactions/<id>")]
async fn get_user_transacctions(id: &str) -> Json<Vec<String>> {
    let db = Dao::new();

    let condition = format!("WHERE guest_id = {}", id);

    let dates = db.get_all::<String>("transactions", 1, &condition).unwrap();
    let listing_ids = db.get_all::<String>("transactions", 4, &condition).unwrap();

    let mut names = Vec::new();
    for listing_id in listing_ids {
        let listing_name = db.get::<String>("listings", 3, &format!("id = {}", listing_id)).unwrap();
        names.push(listing_name);
    }

    let mut output = Vec::new();
    for i in 0..names.len() {
        output.push(format!("{} :: {}", dates[i], names[i]));
    }

    return Json(output)
}

#[post("/updateListing/<new_name>/<new_price>/<description>/<listing_id>")]
async fn update_listing(new_name: &str, new_price: u32, listing_id: &str, description: &str) -> String {
    let new_listing_id = Listing::update(new_name, new_price, description, listing_id);

    if let Some(ref String) = new_listing_id {
        "SUCCESS".to_string()
    } else {
        "ERROR".to_string()
    }
}

#[post("/createTransaction/<start>/<days>/<listing_id>/<guest_id>")]
async fn create_transaction(start: &str, days: i64, guest_id: &str, listing_id: &str) -> String {
    let transaction_id = Transaction::new(start, days, guest_id, listing_id);

    if let Some(ref String) = transaction_id {
        "SUCCESS".to_string()
    } else {
        "ERROR".to_string()
    }
}

#[post("/updateProfile/<id>/<name>/<mail>/<address>/<zip>")]
async fn update_profile(id: &str, name: &str, mail: &str, address: &str, zip: &str) -> String {
    let new_id = User::update(id, name, mail, address, zip);

    if let Some(ref String) = new_id {
        return new_id.unwrap()
    } else {
        "ERROR".to_string()
    }
}

#[get("/updateBalance/<amount>/<id>")]
async fn update_balance(amount: i64, id: &str) -> String {
    let db = Dao::new();
    
    let condition = format!("id = {}", id);
    let balance = db.get::<i64>("users", 6, &condition).unwrap();

    let new_balance = balance + amount;

    db.replace("users", &format!("balance = {}", new_balance), id);

    return new_balance.to_string()
}


#[launch]
fn rocket() -> _ {
    // Create GET handler for all static routes (html pages) within the _relative_ path of "/html"
    let html_routes = FileServer::from(relative!("html"));

    // build server
    rocket::build()
        .mount("/", routes![landing, update_profile, register, login, create_listing, update_listing, create_transaction, get_listings, get_user_listings, update_balance, get_user_transacctions])
        .mount("/", html_routes)
        .attach(CORS)
}
