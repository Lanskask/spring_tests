DROP TABLE IF EXISTS comments CASCADE;

create table comments (
  id serial, -- not null primary key,
  text VARCHAR(500)
--  ,product_id integer
);

DROP TABLE IF EXISTS products CASCADE;
create table products (
  id serial, -- not null primary key,
  name VARCHAR(500),
  description VARCHAR(500),
  rating integer,
  comment_id integer
);

INSERT INTO comments(id, text) VALUES (1, 'VERY LONG COMMENT 1');
--INSERT INTO comments(id, text) VALUES (2, 'VERY LONG COMMENT 2');
--INSERT INTO comments(id, text) VALUES (3, 'VERY LONG COMMENT 3');
--
--insert into products(id, name, rating, comment_id) values (1, 'porage', 5, 1);
--insert into products(id, name, rating, comment_id) values (2, 'juice', 3, 1);

