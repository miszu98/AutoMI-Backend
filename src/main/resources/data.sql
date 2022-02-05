
-- Roles
insert into roles values (1, 'USER');
insert into roles values (2, 'ADMIN');

-- Users

insert into users(id, email, is_active, password, role_id)
values (1, 'roman.nowak@gmail.com', true, '$2a$12$zxR35r8RQ7qsHHoEaQUog.0Bwl/VUGHAPkKrtoZ0FxdC5dV.bQ50e', 1);

insert into users(id, email, is_active, password, role_id)
values (2, 'adrian.kowalski@onet.pl', true, '$2a$12$zxR35r8RQ7qsHHoEaQUog.0Bwl/VUGHAPkKrtoZ0FxdC5dV.bQ50e', 1);

insert into users(id, email, is_active, password, role_id)
values (3, 'admin@automi.com', true, '$2a$12$zxR35r8RQ7qsHHoEaQUog.0Bwl/VUGHAPkKrtoZ0FxdC5dV.bQ50e', 2);

insert into users(id, email, is_active, password, role_id)
values (4, 'adam98@wp.pl', true, '$2a$12$zxR35r8RQ7qsHHoEaQUog.0Bwl/VUGHAPkKrtoZ0FxdC5dV.bQ50e', 1);

-- Colors
insert into colors values (1, 'BLACK');

insert into colors values (2, 'RED');

insert into colors values (3, 'GREY');

insert into colors values (4, 'GREEN');

insert into colors values (5, 'PURPLE');

insert into colors values (6, 'WHITE');

insert into colors values (7, 'SILVER');

-- Driving gears

insert into driving_gears values (1, 'RWD');

insert into driving_gears values (2, 'FWD');

insert into driving_gears values (3, 'AWD');

-- Fuel types

insert into fuel_types values (1, 'GAS');

insert into fuel_types values (2, 'DIESEL');

-- Gearboxes

insert into gearboxes values (1, 'MANUAL');

insert into gearboxes values (2, 'AUTOMATIC');

-- Marks

insert into marks values (1, 'BMW');

insert into marks values (2, 'Mercedes-Benz');

insert into marks values (3, 'Audi');

insert into marks values (4, 'Seat');

insert into marks values (5, 'Opel');

insert into marks values (6, 'Skoda');

insert into marks values (7, 'Volkswagen');

insert into marks values (8, 'Volvo');

insert into marks values (9, 'Kia');

insert into marks values (10, 'Hyundai');

insert into marks values (11, 'Ford');

insert into marks values (12, 'Toyota');

insert into marks values (13, 'Renault');

insert into marks values (14, 'Fiat');

insert into marks values (15, 'Alfa Romeo');

insert into marks values (16, 'Infiniti');

insert into marks values (17, 'Lexus');

-- Models

insert into models values (1, 'M2', 1);

insert into models values (2, 'M3', 1);

insert into models values (3, 'M5', 1);

insert into models values (4, 'E46', 1);

insert into models values (5, 'C63s', 2);

insert into models values (6, 'CLA45s', 2);

insert into models values (7, 'E250', 2);

insert into models values (8, 'C250', 2);

insert into models values (9, 'A35', 2);

insert into models values (10, 'A5', 3);

insert into models values (11, 'A8', 3);

insert into models values (12, 'RS6', 3);

insert into models values (13, 'RS5', 3);


-- Cars

-- insert into cars values (1, 'COUPE', 4300, 0, 550, 'BRAND_NEW', '2021-01-01', 1, 1, 1, 1, 1, 1);
















