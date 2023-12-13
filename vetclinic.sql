--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2023-12-13 17:49:00

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 18652)
-- Name: animal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animal (
    id bigint NOT NULL,
    breed character varying(255),
    color character varying(255),
    date_of_birth date,
    gender character varying(255),
    name character varying(255),
    species character varying(255),
    customer_id integer NOT NULL
);


ALTER TABLE public.animal OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 18651)
-- Name: animal_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animal_customer_id_seq OWNER TO postgres;

--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 216
-- Name: animal_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_customer_id_seq OWNED BY public.animal.customer_id;


--
-- TOC entry 215 (class 1259 OID 18650)
-- Name: animal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animal_id_seq OWNER TO postgres;

--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 215
-- Name: animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;


--
-- TOC entry 221 (class 1259 OID 18664)
-- Name: appointment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    animal_id integer NOT NULL,
    doctor_id integer NOT NULL
);


ALTER TABLE public.appointment OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 18662)
-- Name: appointment_animal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointment_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appointment_animal_id_seq OWNER TO postgres;

--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 219
-- Name: appointment_animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointment_animal_id_seq OWNED BY public.appointment.animal_id;


--
-- TOC entry 220 (class 1259 OID 18663)
-- Name: appointment_doctor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointment_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appointment_doctor_id_seq OWNER TO postgres;

--
-- TOC entry 4860 (class 0 OID 0)
-- Dependencies: 220
-- Name: appointment_doctor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointment_doctor_id_seq OWNED BY public.appointment.doctor_id;


--
-- TOC entry 218 (class 1259 OID 18661)
-- Name: appointment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appointment_id_seq OWNER TO postgres;

--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 218
-- Name: appointment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;


--
-- TOC entry 224 (class 1259 OID 18674)
-- Name: available_date; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.available_date (
    id bigint NOT NULL,
    date date,
    doctor_id integer NOT NULL
);


ALTER TABLE public.available_date OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 18673)
-- Name: available_date_doctor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.available_date_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.available_date_doctor_id_seq OWNER TO postgres;

--
-- TOC entry 4862 (class 0 OID 0)
-- Dependencies: 223
-- Name: available_date_doctor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.available_date_doctor_id_seq OWNED BY public.available_date.doctor_id;


--
-- TOC entry 222 (class 1259 OID 18672)
-- Name: available_date_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.available_date_id_seq OWNER TO postgres;

--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 222
-- Name: available_date_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.available_date_id_seq OWNED BY public.available_date.id;


--
-- TOC entry 226 (class 1259 OID 18682)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 18681)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO postgres;

--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 225
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 228 (class 1259 OID 18691)
-- Name: doctor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);


ALTER TABLE public.doctor OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 18690)
-- Name: doctor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doctor_id_seq OWNER TO postgres;

--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 227
-- Name: doctor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doctor_id_seq OWNED BY public.doctor.id;


--
-- TOC entry 231 (class 1259 OID 18701)
-- Name: vaccine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255),
    protection_finish_date date,
    protection_start_date date,
    animal_id integer NOT NULL
);


ALTER TABLE public.vaccine OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 18700)
-- Name: vaccine_animal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaccine_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vaccine_animal_id_seq OWNER TO postgres;

--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 230
-- Name: vaccine_animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccine_animal_id_seq OWNED BY public.vaccine.animal_id;


--
-- TOC entry 229 (class 1259 OID 18699)
-- Name: vaccine_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vaccine_id_seq OWNER TO postgres;

--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 229
-- Name: vaccine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccine_id_seq OWNED BY public.vaccine.id;


--
-- TOC entry 4664 (class 2604 OID 18655)
-- Name: animal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);


--
-- TOC entry 4665 (class 2604 OID 18656)
-- Name: animal customer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal ALTER COLUMN customer_id SET DEFAULT nextval('public.animal_customer_id_seq'::regclass);


--
-- TOC entry 4666 (class 2604 OID 18667)
-- Name: appointment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);


--
-- TOC entry 4667 (class 2604 OID 18668)
-- Name: appointment animal_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment ALTER COLUMN animal_id SET DEFAULT nextval('public.appointment_animal_id_seq'::regclass);


--
-- TOC entry 4668 (class 2604 OID 18669)
-- Name: appointment doctor_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment ALTER COLUMN doctor_id SET DEFAULT nextval('public.appointment_doctor_id_seq'::regclass);


--
-- TOC entry 4669 (class 2604 OID 18677)
-- Name: available_date id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date ALTER COLUMN id SET DEFAULT nextval('public.available_date_id_seq'::regclass);


--
-- TOC entry 4670 (class 2604 OID 18678)
-- Name: available_date doctor_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date ALTER COLUMN doctor_id SET DEFAULT nextval('public.available_date_doctor_id_seq'::regclass);


--
-- TOC entry 4671 (class 2604 OID 18685)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 4672 (class 2604 OID 18694)
-- Name: doctor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor ALTER COLUMN id SET DEFAULT nextval('public.doctor_id_seq'::regclass);


--
-- TOC entry 4673 (class 2604 OID 18704)
-- Name: vaccine id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine ALTER COLUMN id SET DEFAULT nextval('public.vaccine_id_seq'::regclass);


--
-- TOC entry 4674 (class 2604 OID 18705)
-- Name: vaccine animal_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine ALTER COLUMN animal_id SET DEFAULT nextval('public.vaccine_animal_id_seq'::regclass);


--
-- TOC entry 4837 (class 0 OID 18652)
-- Dependencies: 217
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.animal VALUES (13, 'Boxer', 'Gray', '2023-03-06', 'Female', 'Valentin', 'Dog', 4);
INSERT INTO public.animal VALUES (1, 'King Penguin', 'Teal', '2022-08-15', 'Male', 'Bakes', 'Penguin', 1);
INSERT INTO public.animal VALUES (2, 'Golden', 'Aquamarine', '2022-08-22', 'Female', 'Brimicombe', 'Dog', 1);
INSERT INTO public.animal VALUES (3, 'British', 'Pink', '2022-11-15', 'Female', 'Marsie', 'Cat', 2);
INSERT INTO public.animal VALUES (4, 'Boerboel', 'Yellow', '2023-06-24', 'Female', 'Mahaddie', 'Dog', 2);
INSERT INTO public.animal VALUES (14, 'Boxer', 'Gray', '2023-01-06', 'Male', 'Valentines', 'Dog', 3);
INSERT INTO public.animal VALUES (15, 'Parrot', 'Gray', '2023-01-06', 'Male', 'Speaker', 'Bird', 5);


--
-- TOC entry 4841 (class 0 OID 18664)
-- Dependencies: 221
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.appointment VALUES (1, '2023-12-21 12:00:00', 1, 5);
INSERT INTO public.appointment VALUES (2, '2023-12-21 11:00:00', 4, 5);
INSERT INTO public.appointment VALUES (3, '2023-12-20 15:00:00', 4, 4);
INSERT INTO public.appointment VALUES (4, '2023-12-20 16:00:00', 13, 4);
INSERT INTO public.appointment VALUES (5, '2023-12-20 18:00:00', 14, 4);
INSERT INTO public.appointment VALUES (6, '2023-12-19 11:00:00', 14, 3);
INSERT INTO public.appointment VALUES (7, '2023-12-19 15:00:00', 1, 3);
INSERT INTO public.appointment VALUES (8, '2023-12-17 15:00:00', 1, 1);
INSERT INTO public.appointment VALUES (9, '2023-12-17 11:00:00', 2, 1);
INSERT INTO public.appointment VALUES (10, '2023-12-18 17:00:00', 3, 2);


--
-- TOC entry 4844 (class 0 OID 18674)
-- Dependencies: 224
-- Data for Name: available_date; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.available_date VALUES (1, '2023-12-12', 1);
INSERT INTO public.available_date VALUES (2, '2023-12-13', 2);
INSERT INTO public.available_date VALUES (3, '2023-12-14', 3);
INSERT INTO public.available_date VALUES (4, '2023-12-15', 4);
INSERT INTO public.available_date VALUES (5, '2023-12-16', 5);
INSERT INTO public.available_date VALUES (6, '2023-12-17', 1);
INSERT INTO public.available_date VALUES (7, '2023-12-18', 2);
INSERT INTO public.available_date VALUES (8, '2023-12-19', 3);
INSERT INTO public.available_date VALUES (9, '2023-12-20', 4);
INSERT INTO public.available_date VALUES (10, '2023-12-21', 5);


--
-- TOC entry 4846 (class 0 OID 18682)
-- Dependencies: 226
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer VALUES (1, 'Apt 566', 'Lipnica', 'bshreeves0@cocolog-nifty.com', 'Briant Shreeves', '7666790344');
INSERT INTO public.customer VALUES (2, 'Apt 1033', 'Mar’ina Roshcha', 'kd1@etsy.com', 'Kiah D'' Angelo', '2861579090');
INSERT INTO public.customer VALUES (3, 'Room 746', 'Tirtopuro', 'jmosco2@phpbb.com', 'Jerome Mosco', '1513765112');
INSERT INTO public.customer VALUES (4, 'Room 969', 'Lasiana', 'hmapholm3@gov.uk', 'Harbert Mapholm', '1843023354');
INSERT INTO public.customer VALUES (5, 'PO Box 69685', 'Paltamo', 'rmilstead4@ucla.edu', 'Romain Milstead', '7406708292');


--
-- TOC entry 4848 (class 0 OID 18691)
-- Dependencies: 228
-- Data for Name: doctor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.doctor VALUES (1, '527 Shoshone Place', 'Hor', 'ebotcherby0@pen.io', 'Emlyn Botcherby', '5065644081');
INSERT INTO public.doctor VALUES (2, '5176 Waywood Way', 'Sviblovo', 'anegri1@odnoklassniki.ru', 'Antons Negri', '7913301277');
INSERT INTO public.doctor VALUES (3, '4 Sheridan Parkway', 'Tomepampa', 'tgolightly2@pcworld.com', 'Titos Golightly', '2442395019');
INSERT INTO public.doctor VALUES (4, '717 Ludington Avenue', 'Urrô', 'jgladeche3@merriam-webster.com', 'Jade Gladeche', '3105642607');
INSERT INTO public.doctor VALUES (5, '7473 Loftsgordon Terrace', 'Wang Chan', 'toleszcuk4@arizona.edu', 'Tabb Oleszcuk', '4453275352');


--
-- TOC entry 4851 (class 0 OID 18701)
-- Dependencies: 231
-- Data for Name: vaccine; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.vaccine VALUES (1, 'YMMI', 'Rabies', '2024-04-29', '2023-07-03', 3);
INSERT INTO public.vaccine VALUES (3, 'YANG', 'Rubeola', '2024-09-16', '2023-04-03', 1);
INSERT INTO public.vaccine VALUES (4, 'LPCH', 'Feather', '2024-09-16', '2023-04-03', 1);
INSERT INTO public.vaccine VALUES (6, 'LPCH', 'Feather', '2024-09-16', '2023-04-03', 4);
INSERT INTO public.vaccine VALUES (7, 'LPCH', 'Feather', '2024-09-16', '2023-04-03', 3);
INSERT INTO public.vaccine VALUES (9, 'KJST', 'Leukemia', '2024-06-27', '2023-01-18', 13);
INSERT INTO public.vaccine VALUES (10, 'KJST', 'Leukemia', '2024-06-27', '2023-01-18', 14);
INSERT INTO public.vaccine VALUES (11, 'KJST', 'Leukemia', '2024-06-27', '2023-01-18', 15);
INSERT INTO public.vaccine VALUES (12, 'KJST', 'Leukemia', '2024-06-27', '2023-01-18', 1);


--
-- TOC entry 4868 (class 0 OID 0)
-- Dependencies: 216
-- Name: animal_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_customer_id_seq', 1, false);


--
-- TOC entry 4869 (class 0 OID 0)
-- Dependencies: 215
-- Name: animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_id_seq', 15, true);


--
-- TOC entry 4870 (class 0 OID 0)
-- Dependencies: 219
-- Name: appointment_animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointment_animal_id_seq', 1, false);


--
-- TOC entry 4871 (class 0 OID 0)
-- Dependencies: 220
-- Name: appointment_doctor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointment_doctor_id_seq', 1, false);


--
-- TOC entry 4872 (class 0 OID 0)
-- Dependencies: 218
-- Name: appointment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointment_id_seq', 10, true);


--
-- TOC entry 4873 (class 0 OID 0)
-- Dependencies: 223
-- Name: available_date_doctor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.available_date_doctor_id_seq', 1, false);


--
-- TOC entry 4874 (class 0 OID 0)
-- Dependencies: 222
-- Name: available_date_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.available_date_id_seq', 10, true);


--
-- TOC entry 4875 (class 0 OID 0)
-- Dependencies: 225
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 5, true);


--
-- TOC entry 4876 (class 0 OID 0)
-- Dependencies: 227
-- Name: doctor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctor_id_seq', 9, true);


--
-- TOC entry 4877 (class 0 OID 0)
-- Dependencies: 230
-- Name: vaccine_animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccine_animal_id_seq', 1, false);


--
-- TOC entry 4878 (class 0 OID 0)
-- Dependencies: 229
-- Name: vaccine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccine_id_seq', 12, true);


--
-- TOC entry 4676 (class 2606 OID 18660)
-- Name: animal animal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- TOC entry 4678 (class 2606 OID 18671)
-- Name: appointment appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);


--
-- TOC entry 4680 (class 2606 OID 18680)
-- Name: available_date available_date_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (id);


--
-- TOC entry 4682 (class 2606 OID 18689)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 4684 (class 2606 OID 18698)
-- Name: doctor doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);


--
-- TOC entry 4686 (class 2606 OID 18709)
-- Name: vaccine vaccine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);


--
-- TOC entry 4688 (class 2606 OID 18715)
-- Name: appointment fk2kkeptdxfuextg5ch7xp3ytie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);


--
-- TOC entry 4687 (class 2606 OID 18710)
-- Name: animal fk6pvxm5gfjqxclb651be9unswe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- TOC entry 4690 (class 2606 OID 18725)
-- Name: available_date fkk0d6pu1wxarsoou0x2e1cc2on; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);


--
-- TOC entry 4691 (class 2606 OID 18730)
-- Name: vaccine fkne3kmh8y5pcyxwl4u2w9prw6j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);


--
-- TOC entry 4689 (class 2606 OID 18720)
-- Name: appointment fkoeb98n82eph1dx43v3y2bcmsl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);


-- Completed on 2023-12-13 17:49:00

--
-- PostgreSQL database dump complete
--

