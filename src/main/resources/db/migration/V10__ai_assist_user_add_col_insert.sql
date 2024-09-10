alter table ai_assist_user add column username_ varchar(150) unique;
insert into public.ai_assist_user(username_, password_) values('galikhan.khamitov@gmail.com', '123');