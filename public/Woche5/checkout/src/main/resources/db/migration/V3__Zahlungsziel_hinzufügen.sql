alter table rechnung add zahlungsziel date not null;
update rechnung set zahlungsziel = DATE_ADD(rechnungsdatum, interval 10 day);