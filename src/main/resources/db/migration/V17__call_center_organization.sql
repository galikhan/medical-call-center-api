insert into organization(name_, created_) values('call-center 1305', now());
update medical_call_center_user set organization_ = (select id_ from organization where name_ = 'call-center 1305') where role_ = 'operator' or role_ ='admin';
