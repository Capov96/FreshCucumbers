create table hibernate_sequence (next_val bigint);

insert into hibernate_sequence values ( 1 );

create table review (
    id bigint not null,
    authors_rate integer,
    preview_image varchar(255),
    preview_text text,
    tag varchar(255),
    text text,
    title varchar(255),
    user_id bigint,
    primary key (id));

create table user_role (
    user_id bigint not null,
    roles varchar(255));

create table users (
    id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id));

alter table review
    add constraint review_user_fk
        foreign key (user_id) references users (id);

alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id) references users (id);