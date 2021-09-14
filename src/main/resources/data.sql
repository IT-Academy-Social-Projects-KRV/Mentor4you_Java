INSERT INTO mentor4you_db.users (email, first_name, last_name, password, registration_date, role, status) VALUES
('Mentor1@gmail.com', 'Andrii', 'Andre', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('Mentor2@gmail.com', 'Mykola', 'Myhalkov', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('Mentor3@gmail.com', 'Vasyl', 'Vasyliv', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('Mentee1@gmail.com', 'Vasyl', 'Knyazev', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('Mentee2@gmail.com', 'Vasyl', 'Vasiv', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('Mentee3@gmail.com', 'Vasyl', 'Ivanov', '$2a$10$IYibWmE.bsgqcCcar7L2t.6k.9Q/iO6zpR/CyeBaWuTuMu4nr0OFe', '2021-09-13 10:42:19.236442', 'MENTEE',true);
COMMIT;

INSERT INTO mentor4you_db.accounts (id, last_visit) VALUES
    (1,'2021-09-13 10:42:19.236442'),
    (2,'2021-09-13 10:42:19.236442'),
    (3,'2021-09-13 10:42:19.236442'),
    (4,'2021-09-13 10:42:19.236442'),
    (5,'2021-09-13 10:42:19.236442'),
    (6,'2021-09-13 10:42:19.236442');
COMMIT;

INSERT INTO mentor4you_db.group_services (name) VALUES
('No-no'),
('Yes-yes'),
('Sometimes');
COMMIT;

INSERT INTO mentor4you_db.mentors (id, description, is_offline_in, is_offline_out,
                                    is_online, showable_status, group_services) VALUES
    (1, 'Java', 1, 1, 1, 1, 1),
    (2, 'Java', 1, 1, 1, 1, 2),
    (3, 'Java', 0, 0, 1, 1, 3);
COMMIT;

INSERT INTO mentor4you_db.mentees VALUES
    (4),
    (5),
    (6);
COMMIT;

INSERT INTO mentor4you_db.categories (name) VALUES
    ('Java'),
    ('Pyton'),
    ('HTML'),
    ('TypeScript'),
    ('JavaScript');
COMMIT;

INSERT INTO mentor4you_db.languages (id, name) VALUES
    (1, 'Ukranian'),
    (2, 'English');
COMMIT;

INSERT INTO mentor4you_db.languages_to_accounts (account_id, languages_id) VALUES
(1,1),
(1,2),
(2,2),
(3,1);
COMMIT;

INSERT INTO mentor4you_db.type_contacts (id, name) VALUES
    (1, 'Phone'),
    (2, 'Email'),
    (3, 'Web-site'),
    (4, 'GitHub');
COMMIT;

INSERT INTO mentor4you_db.contacts_to_accounts (contact_data, accounts_id, type_contacts_id) VALUES
    ('+380998877666', 1, 1),
    ('Ololo@meta.ua', 1, 2),
    ('My-sihe URL', 1, 3),
    ('My GitHub URL',1, 4),
    ('+380998877666', 2, 1),
    ('Ololo@meta.ua', 2, 2),
    ('My GitHub URL',3, 4),
    ('My-sihe URL', 3, 3);
COMMIT;



