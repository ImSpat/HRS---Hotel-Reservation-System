CREATE TABLE guest (id bigint not null auto_increment, first_name varchar(255), last_name varchar(255), birth_date date, gender integer, primary key (id))

CREATE TABLE reservation (id bigint not null auto_increment, from_date date, to_date date, confirmed boolean not null, email varchar(255), creation_date timestamp, owner_id bigint, room_id bigint, primary key (id))

CREATE TABLE room (id bigint not null auto_increment, number varchar(255), size integer not null, description varchar(255), primary key (id))

CREATE TABLE room_beds (room_id bigint not null, beds integer)

CREATE TABLE room_photos_urls (room_id bigint not null, photos_urls varchar(255))

ALTER TABLE reservation add constraint FK50156niokhqlv60kjalwhi9wi foreign key (owner_id) references guest

ALTER TABLE reservation add constraint FKm8xumi0g23038cw32oiva2ymw foreign key (room_id) references room

ALTER TABLE room_beds add constraint FKmgdse5awswl23tm83cvmaerh6 foreign key (room_id) references room

ALTER TABLE room_photos_urls add constraint FK8m7edvc6gfyryjne6xluj5stv foreign key (room_id) references room