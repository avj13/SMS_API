--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account (
     id integer NOT NULL,
     auth_id character varying(40),
     username character varying(30)
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_id_seq OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_id_seq OWNED BY account.id;


--
-- Name: phone_number; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

 CREATE TABLE phone_number (
     id integer NOT NULL,
     number character varying(40),
     account_id integer
 );


ALTER TABLE public.phone_number OWNER TO postgres;

--
-- Name: phone_number_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE phone_number_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_number_id_seq OWNER TO postgres;

--
-- Name: phone_number_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE phone_number_id_seq OWNED BY phone_number.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ALTER COLUMN id SET DEFAULT nextval('account_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY phone_number ALTER COLUMN id SET DEFAULT nextval('phone_number_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (id, auth_id, username) VALUES
(1,	'20S0KPNOIM',	'azr1'	),
(2,	'54P2EOKQ47',	'azr2'),
(3, '9LLV6I4ZWI',	'azr3'),
(4,	'YHWE3HDLPQ',	'azr4'),
(5,	'6DLH8A25XZ',	'azr5') ;


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_id_seq', 5, true);

--
-- Data for Name: phone_number; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- COPY phone_number (id, number, account_id) FROM 'C:\Users\avesh\OneDrive\Desktop\data.csv' delimiter ',';


--
-- Name: phone_number_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('phone_number_id_seq', 79, true);


--
-- Name: account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

 ALTER TABLE ONLY account
     ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: phone_number_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

 ALTER TABLE ONLY phone_number
     ADD CONSTRAINT phone_number_pkey PRIMARY KEY (id);


--
-- Name: phone_number_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY phone_number
    ADD CONSTRAINT phone_number_account_id_fkey FOREIGN KEY (account_id) REFERENCES account(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump
--