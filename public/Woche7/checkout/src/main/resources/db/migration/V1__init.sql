drop table if exists bestellposition_dto;
drop table if exists rechnung;
drop table if exists bestellung_dto;
drop table if exists kunde;
drop table if exists produkt_dto;

create table kunde
(
    nr int auto_increment,
    name varchar(300) not null,
    adresse text not null,
    constraint kunde_pk
        primary key (nr)
);

create table bestellung_dto
(
    id int auto_increment,
    datum date not null,
    kunde int not null,
    constraint bestellung_pk
        primary key (id),
    constraint bestellung_kunde_nr_fk
        foreign key (kunde) references kunde (nr)
);

create table produkt_dto
(
    id int auto_increment primary key,
    bezeichnung varchar(400) not null,
    beschreibung text null,
    preis decimal(8,2) default 999999.99 not null,
    bestand int default 0 not null
);

create table rechnung
(
    nr int auto_increment,
    rechnungsdatum date not null,
    bezahldatum date null,
    bestellnr int not null,
    constraint rechnung_pk
        primary key (nr),
    constraint rechnung_bestellung_nr_fk
        foreign key (bestellnr) references bestellung_dto (id)
);

create table bestellposition_dto
(
    id int auto_increment primary key,
    produkt_dto int not null references produkt_dto (id),
    bestellung_dto int not null references bestellung_dto (id),
    anzahl int default 0 not null
);



