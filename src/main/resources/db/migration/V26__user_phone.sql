create table user_phone(
    id_ bigserial,
    user_ bigint references medical_call_center_user(id_),
    phone_ varchar(40),
    created_ timestamp
);
create index idx_user_phone on user_phone(phone_);