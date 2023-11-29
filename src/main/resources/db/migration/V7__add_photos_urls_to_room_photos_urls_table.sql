-- Bugfixing because 3 values are expected in Thymeleaf template in photos_urls but till now only 1 was provided leading to error
insert into room_photos_urls (room_id, photos_urls) values (1, '');
insert into room_photos_urls (room_id, photos_urls) values (1, '');