CREATE TABLE roles(
    id serial PRIMARY KEY,
    name varchar UNIQUE NOT NULL
);

INSERT INTO roles(name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

CREATE TABLE users(
    id serial PRIMARY KEY,
    username varchar UNIQUE NOT NULL,
    password text NOT NULL,
    role_id int REFERENCES roles(id) DEFAULT 1
);
