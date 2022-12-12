create table mc_orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into mc_orders (username, total_price, address, phone)
values ('pinteko', 200, 'address', '12345')