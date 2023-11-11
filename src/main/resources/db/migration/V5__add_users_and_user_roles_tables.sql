CREATE TABLE users (
    id bigint not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
);

CREATE TABLE user_roles(user_id bigint not null, role varchar(255));

ALTER TABLE user_roles ADD constraint FK8m7edvc6gfyryjnssxluj5stv foreign key (user_id) references users (id);

insert into users (id, username, password) values (1, 'jankowalski', '$2a$10$r.WfSWm2lTzO6U0jKU0dGeacDVRwzVORw2JqykxSkfJK/U41PR4my');

insert into user_roles (user_id, role) values (1, 'MANAGER');

insert into users (id, username, password) values (2, 'kasiakowalska', '$2a$10$NRb5TqCYNnYc4vof1yzbn.5UORjpeAHe6YRpNHZQeW4OOvUZ2kTFq');

insert into user_roles (user_id, role) values (2, 'RECEPTION');