-- ALTER TABLE elections
-- ALTER COLUMN category TYPE VARCHAR;
-- ALTER COLUMN schedule TYPE SMALLINT;


TRUNCATE TABLE elections CASCADE;
TRUNCATE TABLE admin CASCADE;

INSERT INTO elections (election_id, title, start_date, end_date, category, schedule, time_created, time_updated, admin_scheduler) VALUES
(11, 'National Election 1', '2024-07-01 08:00:00', '2024-07-01 18:00:00', 'NATIONAL', 'SCHEDULED', now(), now(),12),
(12, 'State Election 1', '2024-07-02 08:00:00', '2024-07-02 18:00:00', 'STATE', 'SCHEDULED', now(), now(),13),
(13, 'LGA Election 1', '2024-07-03 08:00:00', '2024-07-03 18:00:00', 'LGA', 'SCHEDULED', now(), now(),14),
(14, 'Ward Election 1', '2024-07-04 08:00:00', '2024-07-04 18:00:00', 'WARD', 'SCHEDULED', now(), now(), 10),
(15, 'National Election 2', '2024-08-01 08:00:00', '2024-08-01 18:00:00', 'NATIONAL', 'SCHEDULED', now(), now(), 12),
(16, 'State Election 2', '2024-08-02 08:00:00', '2024-08-02 18:00:00', 'STATE', 'SCHEDULED', now(), now(), 12),
(17, 'LGA Election 2', '2024-08-03 08:00:00', '2024-08-03 18:00:00', 'LGA', 'SCHEDULED', now(), now(), 13),
(18, 'Ward Election 2', '2024-08-04 08:00:00', '2024-08-04 18:00:00', 'WARD', 'SCHEDULED', now(), now(), 10),
(19, 'National Election 3', '2024-09-01 08:00:00', '2024-09-01 18:00:00', 'NATIONAL', 'SCHEDULED', now(), now(), 14),
(20, 'State Election 3', '2024-09-02 08:00:00', '2024-09-02 18:00:00', 'STATE', 'SCHEDULED', now(), now(), 13);


INSERT INTO admin (admin_id, first_name, last_name, email, time_created) VALUES
(10, 'Goran', 'Yurkov', 'gyurkov0@hp.com', '2023-11-08 11:09:46'),
(11, 'Penelope', 'Moss', 'pmoss1@cdc.gov', '2024-06-07 06:39:24'),
(12, 'Andrey', 'Macken', 'amacken2@hud.gov', '2023-06-27 10:22:14'),
(13, 'Anders', 'Zannutti', 'azannutti3@is.gd', '2023-11-20 04:51:12'),
(14, 'Codi', 'Kinghorne', 'ckinghorne4@hatena.ne.jp', '2024-05-05 04:41:06');
