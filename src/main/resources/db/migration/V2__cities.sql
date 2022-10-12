CREATE TABLE weather(
    id serial PRIMARY KEY,
    temperature int NOT NULL
);

INSERT INTO weather(temperature) VALUES (30), (60);

CREATE TABLE cities(
    id serial PRIMARY KEY,
    name varchar NOT NULL UNIQUE,
    subscribable boolean NOT NULL DEFAULT true,
    weather_id int REFERENCES weather(id)
);

INSERT INTO cities(name, weather_id) VALUES ('nsk', 1), ('tsk', 2);