CREATE TABLE reservation (
        id bigint not null auto_increment,
        from_date date,
        to_date date,
        confirmed boolean not null,
        email varchar(255),
        creation_date timestamp,
        owner_id bigint,
        room_id bigint,
        primary key (id)
);

ALTER TABLE reservation add constraint FK50156niokhqlv60kjalwhi9wi foreign key (owner_id) references guest(id);

ALTER TABLE reservation add constraint FKm8xumi0g23038cw32oiva2ymw foreign key (room_id) references room(id);