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
-- Data for Name: survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (1, 'Survey first', 'deterministic', true, '2024-10-01 12:17:03.269327', NULL, true);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (3, 'Random survey', 'random', true, '2024-10-01 12:25:52.502725', NULL, true);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (2, 'New Survey', 'deterministic', true, '2024-10-01 12:25:43.129402', NULL, true);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (5, 'Survey1', 'deterministic', true, '2024-10-01 14:31:39.815444', NULL, false);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (4, 'Survey inactive', 'random', true, '2024-10-01 12:27:27.699694', NULL, false);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (6, 'Survey2', 'deterministic', true, '2024-10-01 14:31:46.141781', NULL, false);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (7, 'Survey3', 'random', true, '2024-10-01 14:31:52.204958', NULL, false);
INSERT INTO public.survey (id_, name_, mode_, is_removed_, created_, modified_, is_active_) VALUES (8, 'MainSurvey', 'deterministic', false, '2024-10-01 14:31:58.315042', NULL, true);


--
-- Name: survey_id__seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.survey_id__seq', 8, true);


--
-- PostgreSQL database dump complete
--

