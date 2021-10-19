CREATE TABLE rental.order_items
(
    order_id bigint NOT NULL,
    car_id bigint NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (order_id, car_id)
);
CREATE TABLE rental.cars
(
    id serial,
    model character varying(30) COLLATE pg_catalog."default",
    make character varying(30) COLLATE pg_catalog."default",
    car_class character varying(50) COLLATE pg_catalog."default",
    cost integer,
    status character varying(15) COLLATE pg_catalog."default",
    CONSTRAINT cars_pkey PRIMARY KEY (id)
);
CREATE TABLE rental.orders
(
    id serial,
    userid bigint,
    bill integer,
    status character varying(100) COLLATE pg_catalog."default",
    start_date date,
    end_date date,
    model character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);
CREATE TABLE rental.users
(
    id serial,
    name character varying(30) COLLATE pg_catalog."default",
    password character varying(30) COLLATE pg_catalog."default",
    role character varying(8) COLLATE pg_catalog."default",
    email character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

