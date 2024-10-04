create table dictionary(
    id_ bigserial,
    key_ varchar(100),
    code_ varchar(100),
    name_ varchar(250),
    created_ timestamp default now(),
    primary key(id_),
    unique(key_, code_)
);

alter table question add column category_ bigint references dictionary(id_);
