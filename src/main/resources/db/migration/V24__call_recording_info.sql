create table call_recording_info(
    id_ bigserial primary key,
    appeal_ bigint references appeal(id_),
    recording_file_ varchar(255),
    uniqueid_ varchar(32),
    calldate_ timestamp,
    created_ timestamp
);