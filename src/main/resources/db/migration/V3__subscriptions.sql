CREATE TABLE subscriptions_user_city(
    user_id int REFERENCES users(id),
    city_id int REFERENCES cities(id)
);