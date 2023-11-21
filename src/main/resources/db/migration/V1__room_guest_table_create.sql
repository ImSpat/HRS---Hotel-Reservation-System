CREATE TABLE room (
        id bigint not null auto_increment,
        number varchar(255),
        size integer not null,
        description varchar(255),
        primary key (id)
);

CREATE TABLE room_beds (room_id bigint not null, beds integer);

CREATE TABLE room_photos_urls (room_id bigint not null, photos_urls varchar(255));

ALTER TABLE room_beds add constraint FKmgdse5awswl23tm83cvmaerh6 foreign key (room_id) references room(id);

ALTER TABLE room_photos_urls add constraint FK8m7edvc6gfyryjne6xluj5stv foreign key (room_id) references room(id);

CREATE TABLE guest (
        id bigint not null auto_increment,
        first_name varchar(255),
        last_name varchar(255),
        birth_date date,
        gender integer,
        primary key (id)
);