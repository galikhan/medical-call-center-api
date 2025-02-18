insert into medical_call_center_user(role_, username_, firstname_, lastname_, password_, phone_, fullname_, organization_)
values('specialist', 'spec104', 'Сауле', 'Пернебаева', '77022585158', '77022585158', 'Сауле Пернебаева', (select id_ from organization where name_ like '%ГКП на ПХВ "Сай%'));


--insert into medical_call_center_user(role_, username_, firstname_, lastname_, password_, phone_, fullname_, organization_)
--values('specialist', 'spec105', 'Сауле', 'Пернебаева', '77022585158', '77022585158', 'Сауле Пернебаева', (select id_ from organization where name_ like '%Сайрамская%'));
