create table role(
    code_ varchar(30),
    name_ varchar(50),
    created_ timestamp default now(),
    primary key(code_)
);

create table medical_call_center_user (
    id_ bigserial primary key,
    role_ varchar(30) references role(code_),
    username_ varchar(200),
    firstname_ varchar(100),
    lastname_ varchar(100),
    password_ varchar(40),
    created_ timestamp default now(),
    modified_ timestamp
);

insert into medical_call_center_user(username_, password_) values('admin', '123');


--create type survey_mode as enum('deterministic','random');

