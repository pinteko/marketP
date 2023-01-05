create table groups (
                        id                  bigserial primary key,
                        title               varchar(255)
);
insert into groups(title) values ('spirits'), ('young');
alter table students add column age bigint not null default 18;
alter table students add column group_id bigint not null references groups (id) default 1;