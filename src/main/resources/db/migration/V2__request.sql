
--Жалоба
--Предложение
--Благодарность
--
--Иин
--Фио
--Год рождения
--Пол
--Дата события
--
--Описание жалобы
--
--Откуда звоните
--Лечащий врач/ отделение/участок
--
--Уровень реагирования/важности ситуации

create table appeal_type(
    code_ varchar(50) primary key,
    name_kk_ varchar(100),
    name_ru_ varchar(100),
    is_removed_ boolean default false
);

insert into appeal_type values
('complaint', 'Шағым', 'Жалоба'),
('proposal', 'Ұсыныс', 'Предложение'),
('thanks', 'Алғыс', 'Благодарность');

create table appeal_status(
    code_ varchar(50) primary key,
    name_kk_ varchar(100),
    name_ru_ varchar(100),
    is_removed_ boolean default false
);

insert into appeal_status values
('high', 'Жоғары', 'Высокий'),
('middle', 'Орта', 'Средний'),
('low', 'Төмен', 'Низкий');

create table organization (
    id_ bigserial primary key,
    name_ text,
    created_ timestamp,
    modified_ timestamp default now()
);

create table appeal(

    id_ bigserial primary key,
    type_ varchar(50) references appeal_type(code_),
    status_ varchar(50) references appeal_type(code_),
    description_ text,
    appeal_date_ timestamp,
    organization_ bigint references organization(id_),
    doctor_ bigint references medical_call_center_user(id_),
    applicant_ bigint references medical_call_center_user(id_),
    owner_ bigint references medical_call_center_user(id_),
    created_ timestamp,
    modified_ timestamp default now(),
    is_removed_ boolean default false
);

insert into role values('doctor', 'Врач', now());
insert into role values('admin', 'Админ', now());
insert into role values('applicant', 'Заявитель', now());

alter table medical_call_center_user add column birth_date_ date;
alter table medical_call_center_user add column iin_ varchar(12) unique;