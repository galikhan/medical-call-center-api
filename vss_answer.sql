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
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (5, 1, '22222', NULL, NULL, NULL, '2024-10-02 12:19:40.507118', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (6, 1, '3333', NULL, NULL, NULL, '2024-10-02 12:21:38.849316', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (7, 1, '222', NULL, NULL, true, '2024-10-02 12:26:42.267628', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (4, 1, '1111', NULL, NULL, false, '2024-10-02 12:19:40.472645', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (8, 1, '333', NULL, NULL, false, '2024-10-02 12:26:42.287544', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (9, 1, '33331', NULL, NULL, false, '2024-10-02 12:26:58.139318', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (3, 2, '33', NULL, NULL, true, '2024-10-02 11:38:02.432703', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (12, 2, '33', NULL, NULL, true, '2024-10-03 11:06:17.284748', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (1, 2, 'мужской', NULL, NULL, false, '2024-10-02 11:38:02.246537', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (2, 2, 'женский', NULL, NULL, false, '2024-10-02 11:38:02.429818', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (13, 2, 'neutroal', NULL, NULL, true, '2024-10-03 13:38:41.459953', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (10, 6, 'Поддержка иммунитета', NULL, NULL, false, '2024-10-02 15:13:15.579889', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (11, 6, 'Уровень энергии', NULL, NULL, false, '2024-10-02 15:13:15.59903', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (14, 6, 'Здоровье волос, кожи, ногтей', NULL, NULL, false, '2024-10-03 14:33:55.377299', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (15, 6, 'Управление со стрессом', NULL, NULL, false, '2024-10-03 14:33:55.39774', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (16, 6, 'Память и концентрация', NULL, NULL, false, '2024-10-03 14:33:55.407957', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (17, 6, 'Похудение', NULL, NULL, false, '2024-10-03 14:33:55.462857', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (18, 6, 'Здоровый сон', NULL, NULL, false, '2024-10-03 14:33:55.464289', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (19, 10, 'Нет аллергии', NULL, NULL, false, '2024-10-03 14:39:48.798833', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (20, 10, 'Фрукты (цитрусовые, клубника, малина и т.д.)', NULL, NULL, false, '2024-10-03 14:39:48.805735', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (21, 10, 'Глютен (пшеница, хлебобулочные изделия и т.д.)', NULL, NULL, false, '2024-10-03 14:39:48.809705', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (22, 10, 'Лактоза (молочные продукты)', NULL, NULL, false, '2024-10-03 14:39:48.815397', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (23, 10, 'Рыба и морепродукты', NULL, NULL, false, '2024-10-03 14:39:48.816868', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (24, 10, 'Орехи', NULL, NULL, false, '2024-10-03 14:39:48.827743', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (25, 10, 'Мед', NULL, NULL, false, '2024-10-03 14:39:48.831456', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (26, 10, 'Шоколад', NULL, NULL, false, '2024-10-03 14:39:48.88392', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (27, 10, 'Яйца', NULL, NULL, false, '2024-10-03 14:40:04.655825', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (28, 10, 'Грибы', NULL, NULL, false, '2024-10-03 14:40:04.657149', NULL);
INSERT INTO public.answer (id_, question_, name_, next_question_, is_user_answer_, is_removed_, created_, modified_) VALUES (29, 10, 'Другое', NULL, NULL, false, '2024-10-03 14:40:04.658502', NULL);


--
-- Name: answer_id__seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answer_id__seq', 29, true);


--
-- PostgreSQL database dump complete
--

