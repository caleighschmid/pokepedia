BEGIN TRANSACTION;

DROP TABLE IF EXISTS users_pokemon, users, pokemon;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE pokemon (
    id serial PRIMARY KEY,
    api_id int NOT NULL,
    name varchar(56) NOT NULL UNIQUE,
    base_experience int,
    height int,
    weight int,
    back_url varchar(256),
    front_url varchar(256)
);

CREATE TABLE users_pokemon (
    pokemon_id int NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT fk_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemon (id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

ALTER TABLE users_pokemon
 ADD PRIMARY KEY (pokemon_id, user_id);

COMMIT;
