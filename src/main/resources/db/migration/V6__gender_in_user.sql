create type gender_type as enum('man', 'woman');
alter table appeal add column gender_ gender_type;