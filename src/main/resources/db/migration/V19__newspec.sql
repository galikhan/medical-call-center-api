insert into medical_call_center_user(role_, username_, firstname_, lastname_, password_, phone_, fullname_, organization_)
values('specialist', 'spec103', 'Роза', 'Патсаева', '77014334440', '77014334440', 'Патсаева Роза Алдабергеновна', (select id_ from organization where name_ like '%Тюлькубасская%'));

