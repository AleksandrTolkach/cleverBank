CREATE SCHEMA IF NOT EXISTS application
    AUTHORIZATION cleverbank;

CREATE SEQUENCE IF NOT EXISTS application.accounts_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE application.accounts_id_seq
    OWNER TO cleverbank;

CREATE SEQUENCE IF NOT EXISTS application.bank_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE application.bank_id_seq
    OWNER TO cleverbank;

CREATE SEQUENCE IF NOT EXISTS application.transactions_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE application.transactions_id_seq
    OWNER TO cleverbank;

CREATE SEQUENCE IF NOT EXISTS application.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE application.user_id_seq
    OWNER TO cleverbank;

CREATE TABLE IF NOT EXISTS application.bank
(
    id bigint NOT NULL DEFAULT nextval('application.bank_id_seq'::regclass),
    created_at timestamp without time zone,
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT bank_pk PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS application.bank
    OWNER to cleverbank;

CREATE TABLE IF NOT EXISTS application.users
(
    id bigint NOT NULL DEFAULT nextval('application.user_id_seq'::regclass),
    created_at timestamp without time zone,
    login character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    firstname character varying COLLATE pg_catalog."default",
    lastname character varying COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS application.users
    OWNER to cleverbank;

CREATE TABLE IF NOT EXISTS application.accounts
(
    id bigint NOT NULL DEFAULT nextval('application.accounts_id_seq'::regclass),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    title character varying COLLATE pg_catalog."default",
    sum double precision,
    user_id bigint,
    bank_id bigint,
    CONSTRAINT accounts_pk PRIMARY KEY (id),
    CONSTRAINT accounts_bank_id FOREIGN KEY (bank_id)
    REFERENCES application.bank (id) MATCH SIMPLE
                         ON UPDATE NO ACTION
                         ON DELETE NO ACTION,
    CONSTRAINT accounts_users_id_fk FOREIGN KEY (user_id)
    REFERENCES application.users (id) MATCH SIMPLE
                         ON UPDATE NO ACTION
                         ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS application.accounts
    OWNER to cleverbank;

CREATE TABLE IF NOT EXISTS application.transactions
(
    id bigint NOT NULL DEFAULT nextval('application.transactions_id_seq'::regclass),
    date timestamp without time zone,
    type character varying COLLATE pg_catalog."default",
    sender_bank_id bigint,
    receiver_bank_id bigint,
    sender_account_id bigint,
    receiver_account_id bigint,
    value bigint,
    CONSTRAINT transactions_pk PRIMARY KEY (id),
    CONSTRAINT transactions_accounts_id_fk FOREIGN KEY (sender_account_id)
    REFERENCES application.accounts (id) MATCH SIMPLE
                   ON UPDATE NO ACTION
                   ON DELETE NO ACTION,
    CONSTRAINT transactions_accounts_id_fk_2 FOREIGN KEY (receiver_account_id)
    REFERENCES application.accounts (id) MATCH SIMPLE
                   ON UPDATE NO ACTION
                   ON DELETE NO ACTION,
    CONSTRAINT transactions_bank_id_fk FOREIGN KEY (sender_bank_id)
    REFERENCES application.bank (id) MATCH SIMPLE
                   ON UPDATE NO ACTION
                   ON DELETE NO ACTION,
    CONSTRAINT transactions_bank_id_fk_2 FOREIGN KEY (receiver_bank_id)
    REFERENCES application.bank (id) MATCH SIMPLE
                   ON UPDATE NO ACTION
                   ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS application.transactions
    OWNER to cleverbank;
