--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.24
-- Dumped by pg_dump version 9.6.24

-- Started on 2022-08-15 13:53:25

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
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 18980)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 18978)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO postgres;

--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 185
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- TOC entry 188 (class 1259 OID 19001)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    price double precision NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 18999)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO postgres;

--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 187
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 190 (class 1259 OID 19009)
-- Name: purchases; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchases (
    id integer NOT NULL,
    date date NOT NULL,
    customer_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.purchases OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 19007)
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.purchases_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO postgres;

--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 189
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- TOC entry 2013 (class 2604 OID 18983)
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- TOC entry 2014 (class 2604 OID 19004)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 2015 (class 2604 OID 19012)
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- TOC entry 2142 (class 0 OID 18980)
-- Dependencies: 186
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers (id, firstname, lastname) VALUES (1, 'Андрей', 'Прокопенко');
INSERT INTO public.customers (id, firstname, lastname) VALUES (2, 'Андрей', 'Дмитриев');
INSERT INTO public.customers (id, firstname, lastname) VALUES (3, 'Андрей', 'Егоров');
INSERT INTO public.customers (id, firstname, lastname) VALUES (4, 'Иван', 'Смаль');
INSERT INTO public.customers (id, firstname, lastname) VALUES (5, 'Дмитрий', 'Бурдуков');
INSERT INTO public.customers (id, firstname, lastname) VALUES (6, 'Владимир', 'Борзых');
INSERT INTO public.customers (id, firstname, lastname) VALUES (7, 'Игорь', 'Иванов');
INSERT INTO public.customers (id, firstname, lastname) VALUES (8, 'Андрей', 'Иванов');


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 185
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 8, true);


--
-- TOC entry 2144 (class 0 OID 19001)
-- Dependencies: 188
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products (id, name, price) VALUES (1, 'Пиво', 99.989999999999995);
INSERT INTO public.products (id, name, price) VALUES (2, 'Сигареты', 160.11000000000001);
INSERT INTO public.products (id, name, price) VALUES (3, 'Энергитос', 66);
INSERT INTO public.products (id, name, price) VALUES (4, 'Туалетная бумага', 200.09999999999999);
INSERT INTO public.products (id, name, price) VALUES (5, 'Сыр', 54);


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 187
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 5, true);


--
-- TOC entry 2146 (class 0 OID 19009)
-- Dependencies: 190
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (1, '2022-03-24', 1, 1);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (2, '2022-03-24', 1, 2);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (3, '2022-03-24', 1, 1);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (4, '2022-03-24', 1, 2);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (5, '2022-03-25', 1, 3);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (6, '2022-03-25', 3, 1);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (7, '2022-03-25', 4, 1);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (8, '2013-04-23', 1, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (9, '2013-04-23', 1, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (10, '2013-04-23', 2, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (11, '2013-04-23', 6, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (12, '2013-04-23', 5, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (13, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (14, '2013-04-23', 2, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (15, '2013-04-23', 2, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (16, '2013-04-23', 2, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (17, '2013-04-23', 2, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (18, '2013-04-23', 2, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (19, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (20, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (21, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (22, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (23, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (24, '2013-04-23', 3, 5);
INSERT INTO public.purchases (id, date, customer_id, product_id) VALUES (25, '2013-04-23', 3, 5);


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 189
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchases_id_seq', 25, true);


--
-- TOC entry 2017 (class 2606 OID 18985)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 2019 (class 2606 OID 19006)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 2021 (class 2606 OID 19014)
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 19015)
-- Name: purchases fk_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 2023 (class 2606 OID 19020)
-- Name: purchases fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES public.products(id);


-- Completed on 2022-08-15 13:53:25

--
-- PostgreSQL database dump complete
--

