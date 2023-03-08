CREATE TABLE IF NOT EXISTS  "film" (
                        "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        "name" varchar,
                        "description" varchar,
                        "release_date" date,
                        "duration" int,
                        "rating" varchar
);

CREATE TABLE IF NOT EXISTS  "genre" (
                         "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         "name" varchar
);

CREATE TABLE IF NOT EXISTS  "film_genre" (
                              "film_id" int,
                              "genre_id" int,
                              PRIMARY KEY ("film_id", "genre_id")
);

CREATE TABLE IF NOT EXISTS  "user" (
                        "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        "email" varchar,
                        "login" varchar,
                        "name" varchar,
                        "created_at" timestamp,
                        "country_code" int
);

CREATE TABLE IF NOT EXISTS  "likes" (
                         "film_id" int,
                         "user_id" int,
                         PRIMARY KEY ("film_id", "user_id")
);

CREATE TABLE IF NOT EXISTS  "friends" (
                           "user_id" int,
                           "friend_id" int,
                           "status" boolean NOT NULL DEFAULT false,
                           PRIMARY KEY ("user_id", "friend_id")
);

ALTER TABLE "film_genre" ADD FOREIGN KEY ("film_id") REFERENCES "film" ("id");

ALTER TABLE "film_genre" ADD FOREIGN KEY ("genre_id") REFERENCES "genre" ("id");

ALTER TABLE "likes" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "likes" ADD FOREIGN KEY ("film_id") REFERENCES "film" ("id");

ALTER TABLE "friends" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "friends" ADD FOREIGN KEY ("friend_id") REFERENCES "user" ("id");