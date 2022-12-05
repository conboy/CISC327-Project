#[macro_use] extern crate rocket;

mod users;
mod dao;
mod cors;

use std::path::Path;
use rocket::fs::{NamedFile, FileServer, relative};
use rocket::http::{Cookie, CookieJar};
use crate::users::users::User;
use crate::dao::dao::Dao;
use crate::cors::cors::CORS;


#[get("/")]
async fn landing() -> Option<NamedFile> {
    NamedFile::open(Path::new("html/register.html")).await.ok()
}

#[post("/register/<mail>/<name>/<password>")]
async fn register(mail: &str, name: &str, password: &str, cookies: &CookieJar<'_>) -> &'static str {
    let user: User = User::new(name, mail, password);

    let id: u64 = Dao::calculate_hash(&user);

    let cookie = Cookie::build("user_id", format!("{}", id))
        .path("/updateUserProfile");
    cookies.add(cookie.finish());
    
    "index.html"
}

#[get("/updateUserProfile/<name>/<mail>/<address>/<zip>")]
fn update_profile(name: &str, mail: &str, address: &str, zip: &str, cookies: &CookieJar<'_>) -> &'static str {
    let id = format!("{:?}", cookies.get("user_id").unwrap().value());
        
    User::update(&id, name, mail, address, zip);
    
    "HELLO"
}

#[launch]
fn rocket() -> _ {
    // Create GET handler for all static routes (html pages) within the _relative_ path of "/html"
    let html_routes = FileServer::from(relative!("html"));

    // build server
    rocket::build()
        .mount("/", html_routes)
        .mount("/", routes![landing, update_profile, register])
        .attach(CORS)
}
