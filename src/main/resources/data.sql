INSERT INTO mentor4you_db.users (email, first_name, last_name, password, registration_date, role, status) VALUES
('Mentor1@gmail.com', 'Andrii', 'Andre', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('Mentor2@gmail.com', 'Mykola', 'Myhalkov', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('Mentor3@gmail.com', 'Vasyl', 'Vasyliv', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('Mentee1@gmail.com', 'Vasyl', 'Knyazev', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('Mentee2@gmail.com', 'Vasyl', 'Vasiv', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('Mentee3@gmail.com', 'Vasyl', 'Ivanov', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTEE',true);
COMMIT;
--
-- INSERT INTO mentor4you_db.accounts (id, last_visit, phone_number) VALUES
-- (1,'2021-09-13 10:42:19.236442', '+380996666999'),
-- (2,'2021-09-13 10:42:19.236442', '+380996666999'),
-- (3,'2021-09-13 10:42:19.236442', '+380996666999'),
-- (4,'2021-09-13 10:42:19.236442', '+380996666999'),
-- (5,'2021-09-13 10:42:19.236442', '+380996666999'),
-- (6,'2021-09-13 10:42:19.236442', '+380996666999');
-- COMMIT;
--
-- INSERT INTO mentor4you_db.mentors (id, description, is_offline_in, is_offline_out, is_online, showable_status) VALUES
-- (1, 'Java', 1, 1, 1, 1),
-- (2, 'Java', 1, 1, 1, 1),
-- (3, 'Java', 0, 0, 1, 1);
-- COMMIT;
--
-- INSERT INTO mentor4you_db.mentees VALUES
-- (4),
-- (5),
-- (6);
-- COMMIT;
--
-- # INSERT INTO mentor4you_db.categories (id, name) VALUES
-- #     (1, 'Java'),
-- #     (2, 'Pyton'),
-- #     (3, 'HTML'),
-- #     (4, 'TypeScript'),
-- #     (5, 'JavaScript');
-- # COMMIT;
-- #
-- # INSERT INTO mentor4you_db.mentors_to_categories (id, category_id, currency, mentor_id, rate, mentors_id, mentors_to_categories_id) VALUES
-- # (1, 1,'UAN',1,200,),
-- # (),
-- # ();
-- # COMMIT;
--
-- INSERT INTO mentor4you_db.languages (id, name) VALUES
-- (1, 'Ukranian'),
-- (2, 'English');
-- COMMIT;
--
-- INSERT INTO mentor4you_db.languages_to_accounts (account_id, languages_id) VALUES
-- (1,1),
-- (1,2),
-- (2,2),
-- (3,1);
-- COMMIT;
--
-- INSERT INTO mentor4you_db.social_networks (id, name) VALUES
-- (1, 'LinkedIn'),
-- (2, 'Facebook'),
-- (3, 'YouTube'),
-- (4, 'Instagram');
-- COMMIT;
