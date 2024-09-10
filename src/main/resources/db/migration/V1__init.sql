create table organization (
    id_ bigserial,
    bin_ varchar(12) unique,
    name_ varchar(350),
    parent_ bigint references organization(id_),
    created_ timestamp default now(),
    modified_ timestamp,
    primary key(id_)
);


create table role(
    code_ varchar(30),
    name_ varchar(50),
    created_ timestamp default now(),
    primary key(code_)
);

create table ai_assist_user (
    id_ bigserial,
    organization_ bigint references organization(id_),
    role_ varchar(30) references role(code_),
    firstname_ varchar(100),
    lastname_ varchar(100),
    password_ varchar(40),
    created_ timestamp default now(),
    modified_ timestamp,
    primary key(id_)
);

create type department_type as enum('finance', 'production', 'hr', 'sales', 'project_management', 'it_infrastructure');

create table metrics_category(
    id_ bigserial,
    type_ department_type,
    code_ varchar(100) unique,
    name_ varchar(150),
    parent_ bigint references metrics_category(id_),
    primary key(id_)
);


create table chart_data(

    id_ bigserial,
    category_ bigint references metrics_category(id_),
    code_ varchar(100),
    amount_ numeric,
    when_ timestamp,
    modified_ timestamp,
    created_ timestamp default now(),
    primary key(id_)
);


