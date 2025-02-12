insert into role(code_, name_) values('specialist', 'Специалист');
alter table medical_call_center_user add column organization_ bigint references organization(id_);

insert into medical_call_center_user(role_, username_, firstname_, lastname_, password_, phone_, fullname_, organization_) values
('specialist', 'spec100', 'Жұлдыз', 'Қайратовна', 'k2z0g2r5t', '77787159093', 'Жұлдыз Қайратовна', (select id_ from organization where name_ like '%Казыгуртская%')),
('specialist', 'spec101', 'Гүлдана', '', 'j2t0s2y5', '', 'Гүлдана', (select id_ from organization where name_ ilike '%Жетысайская ц%')),
('specialist', 'spec102', 'name', '', 'o2r0d2b5y', '', '', (select id_ from organization where name_ ilike '%Ордабасинская%'));
