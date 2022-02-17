drop table if exists bestellposition;
drop table if exists rechnung;
drop table if exists bestellung;
drop table if exists kunde;
drop table if exists produkt;

create table kunde
(
    nr int auto_increment,
    name varchar(300) not null,
    adresse text not null,
    constraint kunde_pk
        primary key (nr)
);

create table bestellung
(
    nr int auto_increment,
    datum date not null,
    kunde int not null,
    constraint bestellung_pk
        primary key (nr),
    constraint bestellung_kunde_nr_fk
        foreign key (kunde) references kunde (nr)
);

create table produkt
(
    nr int auto_increment,
    bezeichnung varchar(400) not null,
    beschreibung text null,
    preis decimal(8,2) default 999999.99 not null,
    bestand int default 0 not null,
    constraint produkt_pk
        primary key (nr)
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
        foreign key (bestellnr) references bestellung (nr)
);

create table bestellposition
(
    produkt int not null,
    bestellung int not null,
    anzahl int default 0 not null,
    constraint table_name_pk
        primary key (produkt, bestellung),
    constraint table_name_bestellung_nr_fk
        foreign key (bestellung) references bestellung (nr),
    constraint table_name_produkt_nr_fk
        foreign key (produkt) references produkt (nr)
);



