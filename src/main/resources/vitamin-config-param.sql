-- man 1 delete from vitamin_config_param;
insert into vitamin_config_param(vitamin_config_, question_, answer_) values(1, 2, 1);
insert into vitamin_config_param(vitamin_config_, question_, min_, max_) values(1, 4, 50, 70);
insert into vitamin_config_param(vitamin_config_, question_, answers_) values(1, 6, '{10,11,14}');
insert into vitamin_config_param(vitamin_config_, question_, min_, max_) values(1, 8, 170, 200);
insert into vitamin_config_param(vitamin_config_, question_, min_, max_) values(1, 9, 75, 120);
insert into vitamin_config_param(vitamin_config_, question_, answers_) values(1, 10, '{20,21,22,23}');

--man 2
insert into vitamin_config_param(vitamin_config_, question_, answer_) values(2, 2, 1);
insert into vitamin_config_param(vitamin_config_, question_, min_, max_) values(2, 4, 18, 49);
insert into vitamin_config_param(vitamin_config_, question_, answers_) values(2, 6, '{15,16,17,18}');
insert into vitamin_config_param(vitamin_config_, question_, min_, max_) values(2, 8, 100, 160);
insert into vitamin_config_param(vitamin_config_, question_, min_, max_) values(2, 9, 55, 70);
insert into vitamin_config_param(vitamin_config_, question_, answers_) values(2, 10, '{24,25,26,27}');


update vitamin_config set survey_ = 8;