create table projects (
    id uuid not null,
    description varchar(255),
    name varchar(255) not null,
    primary key (id)
)
create table records (
    id uuid not null,
    description varchar(255),
    end_time timestamp(6),
    start_time timestamp(6) not null,
    project_id uuid,
    user_id uuid not null,
    primary key (id)
)
create table users (
    id uuid not null,
    created_at timestamp(6) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    role smallint array not null,
    updated_at timestamp(6) not null,
    primary key (id)
)

alter table
    if exists records
add
    constraint FKe0lct7m7dyfir8ogwhslsh75 foreign key (project_id) references projects

alter table
    if exists records
add
    constraint FK6p95uajgka0j0dc9vlbjw1sf1 foreign key (user_id) references users