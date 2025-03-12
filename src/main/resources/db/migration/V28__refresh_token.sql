create table refresh_token (
    id_ bigserial primary key,
    user_id_ bigint,
    username_ varchar(200),
    refresh_token_ text,
    creatd_ timestamp
);