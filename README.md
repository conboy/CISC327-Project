# RQbnb

A rust alternative !

        sudo apt install rustup

## Tool Set

This application uses [`rocket.rs`](https://rocket.rs/) as a MVC, as well as
SQLite (`sudo apt install slqite3`) for the database. The front end is simple
HTML/js pages.

## Running

To run the application, use `cargo run` at the root of the directory. Then open
your web-browser at the given IP (you may also just be able to click on the
link directly, depending on your terminal).

## Usage

Register an account with an email, username and password. Once this is done one
can login, edit their profile, and create, edit, or book listings.

## Development

To build the application, use:
        cargo build

To test the application, use:
        cargo test

To format the code, use:
        cargo format

For anything else:
        cargo help
