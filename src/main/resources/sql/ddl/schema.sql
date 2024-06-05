create table films (released_date date not null, id bigint not null auto_increment, description varchar(255) not null, name varchar(255) not null, type enum ('COMEDY','HORROR','SCI_FY'), primary key (id)) engine=InnoDB;
create table ship_film (film_id bigint not null, ship_id bigint not null, primary key (film_id, ship_id)) engine=InnoDB;
create table ships (id bigint not null, description varchar(255) not null, name varchar(255) not null, status enum ('ACTIVE','DESTROYED','INACTIVE','REPAIRING'), type enum ('DESTROYER','FIGHTER','FREIGHTER','GUNSHIP','SHUTTLE','UNNASIGNED','YACHT'), primary key (id)) engine=InnoDB;
alter table ship_film add constraint FKbnp01xkk2y1e103teidpg7s2g foreign key (film_id) references films (id);
alter table ship_film add constraint FK6yfq6fm2sk9859mltiny3q1ew foreign key (ship_id) references ships (id);
