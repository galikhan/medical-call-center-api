alter table appeal drop column status_;
alter table appeal add column status_ varchar(50) references appeal_status(code_);