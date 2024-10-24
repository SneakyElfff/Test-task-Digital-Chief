PGDMP         6            	    |            market     14.13 (Debian 14.13-1.pgdg120+1)     14.13 (Debian 14.13-1.pgdg120+1)                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16384    market    DATABASE     Z   CREATE DATABASE market WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';
    DROP DATABASE market;
                taskuser    false            �            1259    16386    products    TABLE     �   CREATE TABLE public.products (
    id bigint NOT NULL,
    category character varying(255),
    created timestamp(6) without time zone,
    description character varying(255),
    name character varying(255)
);
    DROP TABLE public.products;
       public         heap    taskuser    false            �            1259    16385    products_id_seq    SEQUENCE     �   ALTER TABLE public.products ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          taskuser    false    210            �            1259    16394    skus    TABLE     �   CREATE TABLE public.skus (
    id bigint NOT NULL,
    created timestamp(6) without time zone,
    price double precision,
    quantity integer,
    sku_code character varying(255),
    weight double precision,
    product_id bigint NOT NULL
);
    DROP TABLE public.skus;
       public         heap    taskuser    false            �            1259    16393    skus_id_seq    SEQUENCE     �   ALTER TABLE public.skus ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.skus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          taskuser    false    212                      0    16386    products 
   TABLE DATA           L   COPY public.products (id, category, created, description, name) FROM stdin;
    public          taskuser    false    210   2                 0    16394    skus 
   TABLE DATA           Z   COPY public.skus (id, created, price, quantity, sku_code, weight, product_id) FROM stdin;
    public          taskuser    false    212   O                  0    0    products_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.products_id_seq', 1, false);
          public          taskuser    false    209                       0    0    skus_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.skus_id_seq', 1, false);
          public          taskuser    false    211            |           2606    16392    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            taskuser    false    210            ~           2606    16398    skus skus_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.skus
    ADD CONSTRAINT skus_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.skus DROP CONSTRAINT skus_pkey;
       public            taskuser    false    212                       2606    16399     skus fk49suh4vsoilpii18pb6j8adkp    FK CONSTRAINT     �   ALTER TABLE ONLY public.skus
    ADD CONSTRAINT fk49suh4vsoilpii18pb6j8adkp FOREIGN KEY (product_id) REFERENCES public.products(id);
 J   ALTER TABLE ONLY public.skus DROP CONSTRAINT fk49suh4vsoilpii18pb6j8adkp;
       public          taskuser    false    212    210    3196                  x������ � �            x������ � �     