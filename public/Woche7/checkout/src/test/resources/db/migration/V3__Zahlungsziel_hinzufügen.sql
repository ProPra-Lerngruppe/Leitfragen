alter table rechnung add zahlungsziel date;
update rechnung set zahlungsziel = DATEADD('DAY', 10 ,rechnungsdatum);
alter table rechnung alter column zahlungsziel set not null;