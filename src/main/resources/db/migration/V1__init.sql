create table role(
    code_ varchar(30),
    name_ varchar(50),
    created_ timestamp default now(),
    primary key(code_)
);

create table vostok_shop_survey_user (
    id_ bigserial primary key,
    role_ varchar(30) references role(code_),
    firstname_ varchar(100),
    lastname_ varchar(100),
    password_ varchar(40),
    created_ timestamp default now(),
    modified_ timestamp
);

create type survey_mode as enum('deterministic','random');

create table survey (
    id_ bigserial primary key,
    name_ varchar(250),
    mode_ survey_mode,
    is_removed_ boolean default false,
    created_ timestamp default now(),
    modified_ timestamp

--    owner_ bigint references vostok_shop_survey_user(id_),
);

create type question_type as enum('random_number','single_choice', 'multiple_choice');

create table question (
    id_ bigserial primary key,
    name_ varchar(450),
    survey_ bigint references survey(id_),
    type_ question_type,
    prev_question_ bigint references question(id_),
    next_question_ bigint references question(id_),
    is_removed_ boolean default false,
    created_ timestamp default now(),
    modified_ timestamp
);



create table answer (
    id_ bigserial primary key,
    question_ bigint references question(id_),
    name_ varchar(350),
    next_question_ bigint references question(id_),
    is_user_answer_ boolean default false,
    is_removed_ boolean default false,
    created_ timestamp default now(),
    modified_ timestamp
);


create table city (
    code_ varchar(50),
	name_kk_ varchar(150),
	name_ru_ varchar(150),
	name_en_ varchar(150),
	is_removed_ boolean default false,
	created_ timestamp  default now(),
	modified_ timestamp,
	primary key(code_)
);

insert into city(code_, name_ru_, name_kk_, name_en_) values
('all' ,'Все','Барлығы', 'All'),
('abai' ,'Абай','Абай', 'Abai'),
('akkol' ,'Акколь','Ақкөл', 'Akkol'),
('aksai' ,'Аксай','Ақсай', 'Aksai'),
('aksu' ,'Аксу','Ақсу', 'Aksu'),
('aktau' ,'Актау','Ақтау', 'Aktau'),
('aktobe' ,'Актобе','Ақтөбе', 'Aktobe'),
('alatau' ,'Алатау','Алатау', 'Alatau'),
('alga' ,'Алга','Алға', 'Alga'),
('almaty' ,'Алматы','Алматы', 'Almaty'),
('altai' ,'Алтай','Алтай', 'Altai'),
('aral' ,'Арал','Арал', 'Aral'),
('arkalyk' ,'Аркалык','Арқалық', 'Arkalyk'),
('arys' ,'Арыс','Арыс', 'Arys'),
('astana' ,'Астана','Астана', 'Astana'),
('atbasar' ,'Атбасар','Атбасар', 'Atbasar'),
('atyrau' ,'Атырау','Атырау', 'Atyrau'),
('ayagoz' ,'Аягоз','Аягөз', 'Ayagoz'),
('baikonir' ,'Байконыр','Байқоңыр', 'Baikonir'),
('balkhash' ,'Балхаш','Балқаш', 'Balkhash'),
('bulaevo' ,'Булаево','Булаев', 'Bulaevo'),
('derzhavinsk' ,'Державинск','Державин', 'Derzhavinsk'),
('ereymentau' ,'Ерейментау','Ерейментау', 'Ereymentau'),
('esik' ,'Есик','Есік', 'Esik'),
('esil', 'Есиль','Есіл','Esil'),
('zhanaozen' ,'Жанаозен','Жаңаөзен', 'Zhanaozen'),
('zhanatas' ,'Жанатас','Жаңатас', 'Zhanatas'),
('zharkent' ,'Жаркент','Жаркент', 'Zharkent'),
('zhezkazgan' ,'Жезказган','Жезқазған', 'Zhezkazgan'),
('zhem' ,'Жем','Жем', 'Zhem'),
('zhetysai' ,'Жетысай','Жетісай', 'Zhetysai'),
('zhitikara' ,'Житикара','Жітіқара', 'Zhitikara'),
('zaysan' ,'Зайсан','Зайсаң', 'Zaysan'),
('kazalinsk' ,'Казалинск','Қазалы', 'Kazalinsk'),
('kandyagash' ,'Кандыагаш','Қандыағаш', 'Kandyagash'),
('karagandy' ,'Караганды','Қарағанды', 'Karagandy'),
('karazhal' ,'Каражал','Қаражал', 'Karazhal'),
('karatau' ,'Каратау','Қаратау', 'Karatau'),
('karkaralinsk' ,'Каркаралинск','Қарқаралы', 'Karkaralinsk'),
('kaskelen' ,'Каскелен','Қаскелең', 'Kaskelen'),
('kentau' ,'Кентау','Кентау', 'Kentau'),
('kokshetau' ,'Кокшетау','Көкшетау', 'Kokshetau'),
('konaev' ,'Конаев','Қонаев', 'Konaev'),
('kostanay' ,'Костанай','Қостанай', 'Kostanay'),
('kosshy' ,'Косшы','Қосшы', 'Kosshy'),
('kulsary' ,'Кулсары','Құлсары', 'Kulsary'),
('kurchatov' ,'Курчатов','Курчатов', 'Kurchatov'),
('kyzylorda' ,'Кызылорда','Қызылорда', 'Kyzylorda'),
('lenger' ,'Ленгер','Леңгір', 'Lenger'),
('lisakovsk' ,'Лисаковск','Лисаковск', 'Lisakovsk'),
('makinsk' ,'Макинск','Макинск', 'Makinsk'),
('mamlyutka' ,'Мамлютка','Мамлют', 'Mamlyutka'),
('pavlodar' ,'Павлодар','Павлодар', 'Pavlodar'),
('petropavlovsk' ,'Петропавловск','Петропавл', 'Petropavlovsk'),
('priozersk' ,'Приозёрск','Приозер', 'Priozersk'),
('ridder' ,'Риддер','Риддер', 'Ridder'),
('rudny' ,'Рудный','Рудный', 'Rudny'),
('saran' ,'Сарань','Саран', 'Saran'),
('sarkand' ,'Сарканд','Сарқант', 'Sarkand'),
('saryagash' ,'Сарыагаш','Сарыағаш', 'Saryagash'),
('satpayev' ,'Сатпаев','Сәтбаев', 'Satpayev'),
('semey' ,'Семей','Семей', 'Semey'),
('sergeevka' ,'Сергеевка','Сергеев', 'Sergeevka'),
('serebryansk' ,'Серебрянск','Серебрянск', 'Serebryansk'),
('stepnogorsk' ,'Степногорск','Степногорск', 'Stepnogorsk'),
('stepnyak' ,'Степняк','Степняк', 'Stepnyak'),
('taiynsha' ,'Тайынша','Тайынша', 'Taiynsha'),
('talgar' ,'Талгар','Талғар', 'Talgar'),
('taldykorgan' ,'Талдыкорган','Талдықорған', 'Taldykorgan'),
('taraz' ,'Тараз','Тараз', 'Taraz'),
('tekeli' ,'Текели','Текелі', 'Tekeli'),
('temir' ,'Темир','Темір', 'Temir'),
('temirtau' ,'Темиртау','Теміртау', 'Temirtau'),
('tobyl' ,'Тобыл','Тобыл', 'Tobyl'),
('turkestan' ,'Туркестан','Түркістан', 'Turkestan'),
('uralsk' ,'Уральск','Орал', 'Uralsk'),
('ust-kamenogorsk', 'Усть-Каменогорск','Өскемен', 'Ust-Kamenogorsk'),
('usharal', 'Ушарал','Үшарал', 'Usharal'),
('ushtobe', 'Уштобе','Үштөбе', 'Ushtobe'),
('fort-shevchenko', 'Форт-Шевченко','Форт-Шевченко', 'Fort-Shevchenko'),
('khromtau', 'Хромтау','Хромтау', 'Khromtau'),
('shalkar', 'Шалкар','Шалқар', 'Shalkar'),
('ball', 'Шар','Шар', 'Ball'),
('shardara', 'Шардара','Шардара', 'Shardara'),
('shakhtinsk', 'Шахтинск','Шахтинск', 'Shakhtinsk'),
('shemonaikha', 'Шемонаиха','Шемонаиха', 'Shemonaikha'),
('shu', 'Шу','Шу', 'Shu'),
('shymkent', 'Шымкент','Шымкент', 'Shymkent'),
('schuchinsk', 'Щучинск','Щучинск', 'Schuchinsk'),
('ekibastuz', 'Экибастуз','Екібастұз', 'Ekibastuz'),
('emba', 'Эмба','Ембі', 'Emba');

create type gender_type as enum('male','female');

create table participant (
    id_ bigserial primary key,
    firstname_ varchar(100),
    email_ varchar(200),
    height_ real,
    weight_ real,
    age_ integer,
    gender_ gender_type,
    aim_ bigint references answer(id_),
    city_ varchar(50) references city(code_),
    allergy_ bigint references answer(id_),
    created_ timestamp default now(),
    modified_ timestamp
);

create table participant_answer (
    id_ bigserial primary key,
    participant_ bigint references participant(id_),
    survey_ bigint references survey(id_),
    question_ bigint references question(id_),
    answer_ bigint references answer(id_),
    created_ timestamp default now(),
    modified_ timestamp
);

create table vitamin_category(
    id_ bigserial primary key,
    name_ varchar(350),
    created_ timestamp default now()
);

create table vitamin(
    id_ bigserial primary key,
    category_ bigint references vitamin_category(id_),
    name_ varchar(350),
    description_ text,
    created_ timestamp default now(),
    modified_ timestamp
);

create table vitamin_config(
    id_ bigserial primary key,
    name_ varchar(250),
    description_ text,
    height_min_ real,
    height_max_ real,
    weight_min_ real,
    weight_max_ real,
    gender_ gender_type,
    age_min_ integer,
    age_max_ integer,
    city_ text [],
    aim_ bigint references answer(id_),
    allergy_ bigint references answer(id_),
    vitamins_ bigint [],
    created_ timestamp default now(),
    modified_ timestamp
);

create table vitamin_config_extra(
    id_ bigserial primary key,
    vitamin_config_ bigint references vitamin_config(id_),
    question_ bigint references question(id_),
    answer_ bigint references answer(id_),
    created_ timestamp default now(),
    modified_ timestamp
);






