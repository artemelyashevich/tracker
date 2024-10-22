create table if not exists projects (
    id uuid not null,
    description varchar(2000),
    name varchar(255) not null,
    primary key (id)
)
create table if not exists records (
    id uuid not null,
    description varchar(2000),
    end_time timestamp(6),
    start_time timestamp(6) not null,
    project_id uuid,
    user_id uuid not null,
    primary key (id)
)
create table if not exists users (
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
    constraint records_projects_fk foreign key (project_id) references projects

alter table
    if exists records
add
    constraint records_users_fk foreign key (user_id) references users