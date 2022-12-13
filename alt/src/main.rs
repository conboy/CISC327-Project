#[macro_use] extern crate rocket;

mod users;
mod dao;
mod cors;
mod transactions;

use std::path::Path;
use rocket::fs::{NamedFile, FileServer, relative};
use rocket::http::{Cookie, CookieJar};
use crate::users::users::User;
use crate::dao::dao::Dao;
use crate::cors::cors::CORS;
use crate::transactions::transactions::Transaction;


#[get("/")]
async fn landing() -> Option<NamedFile> {
    NamedFile::open(Path::new("html/register.html")).await.ok()
}


#[get("/home")]
async fn home() -> Option<NamedFile> {
    NamedFile::open(Path::new("html/index.html")).await.ok()
}

#[get("/test/<param>")]
async fn test(param: usize) -> String{
    let db = Dao::new();
    let data = db.get::<String>("users",param,"6864798263328938362");

    if let Ok(ref String) = data {
        format!("{}", data.unwrap())
    } else {
        format!("{}", data.unwrap())
    }
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

#[post("/updateProfile/<id>/<name>/<mail>/<address>/<zip>")]
async fn update_profile(id: &str, name: &str, mail: &str, address: &str, zip: &str) -> String {

    let new_id = User::update(id, name, mail, address, zip);

    if let Some(ref String) = new_id {
        return new_id.unwrap()
    } else {
        "ERROR".to_string()
    }
}

#[launch]
fn rocket() -> _ {
    // Create GET handler for all static routes (html pages) within the _relative_ path of "/html"
    let html_routes = FileServer::from(relative!("html"));

    // build server
    rocket::build()
        .mount("/", routes![landing, update_profile, register, test, login])
        .mount("/", html_routes)
        .attach(CORS)
}
