

DROP TABLE IF EXISTS comments CASCADE;
create table comments (
  id serial not null primary key,
  text VARCHAR(500),
  product_id integer
);

DROP TABLE IF EXISTS products CASCADE;
create table products (
  id serial not null primary key,
  name VARCHAR(500),
  description VARCHAR(500),
  rating integer,
  comment_id integer
);