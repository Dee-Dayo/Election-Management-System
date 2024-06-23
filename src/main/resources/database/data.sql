truncate table elections cascade;
truncate table admin cascade;
truncate table candidates cascade;
truncate table voters cascade;
truncate table votes cascade;


insert into elections(election_id, title, start_date, end_date, category, schedule, time_created) values
    (200, 'Presidential Elections', '2024-07-19T09:00', '2024-07-19T01:00', 'NATIONAL', 'SCHEDULED',
     '2024-06-19T09:52:36.836436300');


insert into admin (admin_id, email, first_name, last_name, password, role, time_created, time_updated) values
    (1, 'admin@example.com', 'Admin', 'User', '$2a$10$DowJNTRMi8Wmdfi6r4RrHeqz9hE72lpeu2t0FZrI8j/xIftoJjFEi',
     'ADMIN', NOW(), NOW());




insert into candidates(candidate_id, first_name, last_name, username, email, password, position_contested,
                       party_affiliation, election_id, time_created) values
(100, 'Adewale', 'Adebowale', 'A2', 'Ajibolaphilip10@gmail.com', 'password', 'NATIONAL', 'PDP', 200,
 '2024-06-19T09:52:36.836436300'),
(101, 'Olatunbosun', 'Adeotun', 'Bosun', 'praiseoyewole560@gmail.com', 'password', 'NATIONAL', 'APC', 200,
 '2024-06-19T09:52:36.836436300'),
(102, 'Gbenga', 'Akeem', 'Gbenga', 'victormsonter@gmail.com', 'password', 'NATIONAL', 'LP', 200,
 '2024-06-19T09:52:36.836436300'),
(103, 'Patience', 'Goodluck', 'Pg', 'aramideotenaike@gmail.com', 'password', 'NATIONAL', 'YPP', 200,
 '2024-06-19T09:52:36.836436300');

insert into votes(vote_id, affiliation,election_election_id) values
(100,'PDP',200),
(101,'APC',200),
(102,'LP',200),
(104,'PDP',200),
(105,'APC',200),
(106,'LP',200),
(107,'APC',200),
(108,'APC',200);


insert into voters(voter_id, first_name, last_name, date_of_birth, state_of_origin, email, password, time_created)
values
(200, 'Rebecca', 'Nelson', '20-05-1999', 'Lagos', 'Darhyor2050@gmail.com', 'password', '2024-06-19T09:52:36.836436300'),
(201, 'Bankole', 'Taiwo', '20-05-2000', 'Ogun', 'babsmoh12@gmail.com', 'password', '2024-06-19T09:52:36.836436300'),
(202, 'Rukkayah', 'Akeem', '20-05-1970', 'Delta', 'Josephfeyishetan@gmail.com', 'password', '2024-06-19T09:52:36.836436300'),
(203, 'Faith', 'Joseph', '20-05-1991', 'Osun', 'm.yaro@native.semicolon.africa', 'password', '2024-06-19T09:52:36.836436300');



