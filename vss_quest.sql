--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Postgres.app)
-- Dumped by pg_dump version 14.11 (Postgres.app)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (7, 'Пожалуйста, укажите свою почту, а затем перейдём к самому важному.', 8, 'input', NULL, NULL, false, '2024-10-02 15:13:39.083176', NULL, '(Мы отправим рекомендацию, чтобы она всегда была под рукой)', 4);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (3, 'yyy', 7, 'multiple_choice', NULL, NULL, false, '2024-10-01 22:46:17.021175', NULL, NULL, NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (2, 'Ваш пол?', 8, 'single_choice', NULL, NULL, false, '2024-10-01 22:38:04.74682', NULL, '', NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (5, 'В каком городе вы живёте?', 8, 'lazy_loaded_list', NULL, NULL, false, '2024-10-02 15:12:50.631985', NULL, '(мы доставляем в Астана и Алматы)', NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (6, 'Выберите основные цели:', 8, 'multiple_choice', NULL, NULL, false, '2024-10-02 15:13:03.894417', NULL, '( Они будут играть ключевую роль в вашем приёме витаминов. При выборе также учтем второстепенные цели, можете выбрать несколько  вариантов)', NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (8, 'Какой у вас рост (см)?', 8, 'random_number', NULL, NULL, false, '2024-10-03 14:37:28.469435', NULL, '', NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (9, 'Какой у вас вес (кг)?', 8, 'random_number', NULL, NULL, false, '2024-10-03 14:37:45.39067', NULL, '', NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (10, 'Питание: У вас есть какие-либо аллергии к пище?', 8, 'multiple_choice', NULL, NULL, false, '2024-10-03 14:38:23.841886', NULL, '(Можете выбрать один и более вариантов )', NULL);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (1, 'Как вас зовут?', 8, 'input', NULL, NULL, false, '2024-10-01 22:37:50.129203', NULL, '(Мы укажем ваше имя в наборе витаминов — так вы узнаете, что он подобран персонально для вас)', 1);
INSERT INTO public.question (id_, name_, survey_, type_, prev_question_, next_question_, is_removed_, created_, modified_, description_, category_) VALUES (4, 'Укажите дату рождения?', 8, 'date', NULL, NULL, false, '2024-10-02 15:12:41.59529', NULL, '(Мы назначаем разные дозировки витаминов, в зависимости от возраста. Если вы младше 18 лет, перед приёмом витаминов необходимо проконсультироваться с врачом)', 5);


--
-- Name: question_id__seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_id__seq', 10, true);


--
-- PostgreSQL database dump complete
--

