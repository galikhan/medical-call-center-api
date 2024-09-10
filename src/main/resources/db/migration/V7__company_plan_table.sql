create table company_plan(
    id_ bigserial,
    organization_ bigint references organization(id_),
    code_ varchar(100) references metrics_category(code_),
    amount_ numeric,
    modified_ timestamp,
    created_ timestamp default now()
);