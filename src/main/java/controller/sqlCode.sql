/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  alumne
 * Created: 11 mar 2024
 */



CREATE TABLE USUARIOS
(
    "ID" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    "NOMBRE" VARCHAR(100) not null,
    "APELLIDO" VARCHAR(100) not null,
    "CORREO" VARCHAR(100) not null,
    "USERNAME" VARCHAR(255) not null,
    "PASSWORD" VARCHAR(255) not NULL
);


CREATE TABLE VIDEOS
(
    "ID" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    "AUTORID" INT not null,
    "TITULO" VARCHAR(100) not null,
    "AUTOR" VARCHAR(100) not null,
    "FECHA_CREACION" DATE not null,
    "DURACION" TIME not null,
    "REPRODUCCIONES" INT not null,
    "DESCRIPCION" VARCHAR(255) not null,
    "FORMATO" VARCHAR(5) not null,
    "URL" VARCHAR(255) not null,
    "URL_INFO" VARCHAR(255) not null,
    "STREAM" INT not null
);