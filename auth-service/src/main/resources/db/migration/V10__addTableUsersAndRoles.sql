create table users (id bigserial, username varchar(30) not null, password varchar(80) not null,
                email varchar(50) UNIQUE, primary key (id));

create table roles (id serial, name varchar(50) not null, primary key (id));

create table users_roles (user_id bigint not null, role_id int not null,
        primary key (user_id, role_id), foreign key (user_id) references users (id),
        foreign key (role_id) references  roles (id));

insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN'), ('READ_PROFILE');

insert into users (username, password, email)
values  ('pinteko', '$2a$12$ys/TFm3SHmS21DLMdqbG3elWQYy5a51xRoiIqAttb9.Z9eeXA5Eqa', 'pinteko@mail.ru'),
        ('nasik', '$2a$12$Tiejobd.aTLiKkHah/O4R.w/9AGz2/TjeCwm6ftgxYo.3ISm2IRBG', 'nasik@mail.ru'),
        ('user', '$2a$12$ggR2TQsVeTMhaWJerKV7u.mMBQkIlEVXO3TwBpP4LfZEmKqvh9t42', 'user@mail.ru');

insert into users_roles (user_id, role_id) values (1, 2), (2, 2), (3, 1), (1, 1), (2, 1), (1, 3);