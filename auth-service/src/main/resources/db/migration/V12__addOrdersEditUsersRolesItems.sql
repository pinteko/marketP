create table orders (
    id                  bigserial primary key,
    user_id             bigint not null references users (id),
    total_price         double precision not null,
    address             varchar(255),
    phone               varchar(255),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table order_items (
    id                  bigserial primary key,
    novel_id            bigint not null references novels (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   double precision null,
    price               double precision null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

alter table users add column created_at timestamp default current_timestamp;
alter table users add column updated_at timestamp default current_timestamp;

alter table roles add column created_at timestamp default current_timestamp;
alter table roles add column updated_at timestamp default current_timestamp;

alter table novels add column created_at timestamp default current_timestamp;
alter table novels add column updated_at timestamp default current_timestamp;

insert into orders(user_id, total_price, address, phone) values
(1, 21.57, 'address', '2355634');

insert into order_items(novel_id, order_id, quantity, price_per_product, price) values
(1, 1, 1, 12.45, 12.45), (2, 1, 1, 9.12, 9.12);