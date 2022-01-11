create table bestellung
(
    nr int auto_increment,
    constraint bestellung_pk
        primary key (nr)
);

create table produkt
(
    nr int auto_increment,
    bezeichnung varchar(400) not null,
    preis decimal(8,2) default 999999.99 not null,
    constraint produkt_pk
        primary key (nr)
);

create table bestellposition
(
    produkt int not null,
    bestellung int not null,
    anzahl int default 0 not null
);

INSERT INTO produkt (nr, bezeichnung, preis) VALUES (1, 'a', 2.99);
INSERT INTO produkt (nr, bezeichnung, preis) VALUES (2, 'b', 1.98);
INSERT INTO produkt (nr, bezeichnung, preis) VALUES (3, 'c', 0.49);

INSERT into bestellung (nr) VALUES (1);
INSERT into bestellung (nr) VALUES (2);
INSERT into bestellung (nr) VALUES (3);

INSERT INTO bestellposition (produkt, bestellung, anzahl) VALUES ( 1,2,921);
INSERT INTO bestellposition (produkt, bestellung, anzahl) VALUES ( 2,2,29871);
INSERT INTO bestellposition (produkt, bestellung, anzahl) VALUES ( 3,2,5699);