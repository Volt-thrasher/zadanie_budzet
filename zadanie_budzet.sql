create table transactions (
id int(4) auto_increment primary key,
typ varchar(10) not null,
opis varchar(120),
kwota double,
data_transakcji date);
