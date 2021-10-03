INSERT INTO mentor4you_db.users (avatar, email, first_name, last_name, password, registration_date, role, status) VALUES
('https://avatarmaker.net/images/1.png','Admin1@gmail.com', 'Admin', 'Admino', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'ADMIN',true),
('https://avatarmaker.net/images/1.png','Moderator1@gmail.com', 'Moder', 'Mod', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MODERATOR',true),
('https://avatarmaker.net/images/1.png','Moderator2@gmail.com', 'Mod', 'Moder', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MODERATOR',true),
('https://avatarmaker.net/images/1.png','Moderator3@gmail.com', 'Modest', 'Moderovich', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MODERATOR',true),

('https://avatarmaker.net/images/1.png','Mentor1@gmail.com', 'Andrii', 'Android', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('https://avatarmaker.net/images/1.png','Mentor2@gmail.com', 'Mykola', 'Myhalkov', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('https://avatarmaker.net/images/1.png','Mentor3@gmail.com', 'Vasyl', 'Vasyliv', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('https://avatarmaker.net/images/1.png','Mentor4@gmail.com', 'Ivan', 'Ivanov', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTOR',true),
('https://avatarmaker.net/images/1.png','Mentor5@gmail.com', 'Leonard', 'Vasyliv', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTOR',true),

('https://avatarmaker.net/images/1.png','Mentee1@gmail.com', 'Orest', 'Knyazev', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('https://avatarmaker.net/images/1.png','Mentee2@gmail.com', 'Dmytro', 'Vasiv', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('https://avatarmaker.net/images/1.png','Mentee3@gmail.com', 'Bogdan', 'Ivanov', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('https://avatarmaker.net/images/1.png','Mentee4@gmail.com', 'Peter', 'Kwit', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTEE',true),
('https://avatarmaker.net/images/1.png','Mentee5@gmail.com', 'Herasim', 'Nechai', '$2a$10$iFfOLYfhGHPURIbw5kWwlepRKL5YCQ9ye7KItSGBrogUn3M9H5RLu', '2021-09-13 10:42:19.236442', 'MENTEE',true);
COMMIT;

INSERT INTO mentor4you_db.accounts (id, last_visit) VALUES
(1,'2021-09-13 10:42:19.236442'),
(2,'2021-09-13 10:42:19.236442'),
(3,'2021-09-13 10:42:19.236442'),
(4,'2021-09-13 10:42:19.236442'),
(5,'2021-09-13 10:42:19.236442'),
(6,'2021-09-13 10:42:19.236442'),
(7,'2021-09-13 10:42:19.236442'),
(8,'2021-09-13 10:42:19.236442'),
(9,'2021-09-13 10:42:19.236442'),
(10,'2021-09-13 10:42:19.236442'),
(11,'2021-09-13 10:42:19.236442'),
(12,'2021-09-13 10:42:19.236442'),
(13,'2021-09-13 10:42:19.236442'),
(14,'2021-09-13 10:42:19.236442');
COMMIT;

INSERT INTO mentor4you_db.mentors (id, description, is_offline_in, is_offline_out,
                                   is_online, showable_status, group_serv) VALUES
(5, 'Java', 1, 1, 1, 1, 'YES'),
(6, 'Web', 1, 1, 1, 1, 'YES'),
(7, 'ArtI', 1, 0, 1, 1, 'YES'),
(8, 'Design', 0, 1, 1, 1, 'YES'),
(9, 'Mobile', 0, 0, 1, 1, 'YES');
COMMIT;

INSERT INTO mentor4you_db.mentees VALUES
(10),
(11),
(12),
(13),
(14);
COMMIT;

INSERT INTO mentor4you_db.categories (name) VALUES
('Java'),
('Pyton'),
('HTML'),
('TypeScript'),
('JavaScript'),
('Kotlin'),
('CSS');
COMMIT;

INSERT INTO mentor4you_db.languages (id, name) VALUES
(1, 'Ukranian'),
(2, 'English');
COMMIT;

INSERT INTO mentor4you_db.languages_to_accounts (account_id, languages_id) VALUES
(1,1),(1,2),
(2,1),(2,2),
(3,1),(3,2),
(4,1),(4,2),
(5,1),(5,2),
(6,1),(6,2),
(7,1),(7,2),
(8,1),(8,2),
(9,1),(9,2),
(10,1),(10,2),
(11,1),(11,2),
(12,1),(12,2),
(13,1),(13,2),
(14,1),(14,2);
COMMIT;

INSERT INTO mentor4you_db.type_contacts (id, name) VALUES
(1, 'PhoneNumFirst'),
(2, 'PhoneNumSecond'),
(3, 'LinkedIn'),
(4, 'Facebook'),
(5, 'Telegram'),
(6, 'Skype'),
(7, 'GitHub'),
(8, 'Email');
COMMIT;

INSERT INTO mentor4you_db.contacts_to_accounts (contact_data, accounts_id, type_contacts_id) VALUES
('+380998877666', 1, 1), ('Admin1@gmail.com', 1, 8), ('My-site URL', 1, 3), ('My GitHub URL',1, 7),
('+380998877667', 2, 1), ('Moderator1@gmail.com',2,8),
('+380998877668', 3, 1), ('Moderator2@gmail.com',3,8),
('+380998877669', 4, 1), ('Moderator3@gmail.com',4,8),

('+380998877670', 5, 1), ('Mentor1@gmail.com', 5, 8), ('My-site URL', 5, 3), ('My GitHub URL',5, 7),
('+380998877671', 6, 1), ('Mentor2@gmail.com', 6, 8), ('My-site URL', 6, 3), ('My GitHub URL',6, 7),
('+380998877672', 7, 1), ('Mentor3@gmail.com', 7, 8), ('My-site URL', 7, 3), ('My GitHub URL',7, 7),
('+380998877673', 8, 1), ('Mentor4@gmail.com', 8, 8), ('My-site URL', 8, 3), ('My GitHub URL',8, 7),
('+380998877674', 9, 1), ('Mentor5@gmail.com', 9, 8), ('My-site URL', 9, 3), ('My GitHub URL',9, 7),

('+380998877675', 9, 1), ('Mentee1@gmail.com', 9, 8), ('My-site URL', 9, 3), ('My GitHub URL',9, 7),
('+380998877676', 9, 1), ('Mentee2@gmail.com', 9, 8), ('My-site URL', 9, 3), ('My GitHub URL',9, 7),
('+380998877677', 9, 1), ('Mentee3@gmail.com', 9, 8), ('My-site URL', 9, 3), ('My GitHub URL',9, 7),
('+380998877678', 9, 1), ('Mentee4@gmail.com', 9, 8), ('My-site URL', 9, 3), ('My GitHub URL',9, 7),
('+380998877679', 9, 1), ('Mentee5@gmail.com', 9, 8), ('My-site URL', 9, 3), ('My GitHub URL',9, 7);
COMMIT;

INSERT INTO mentor4you_db.mentors_to_categories (id, currency, rate,categories_id ,mentors_id) VALUES
(1, 'UAH', 150, 1,5),
(4, 'UAH', 175, 2,6),
(2, 'USD', 10, 2,7),
(5, 'UAH', 230, 3,8),
(6, 'UAH', 250, 4,9);
COMMIT;

INSERT INTO mentor4you_db.educations (id,description,name,education) VALUES
(1,'description','higher education',5),
(2,'description','higher education',6),
(3,'description','higher education',7),
(4,'description','higher education',8),
(5,'description','higher education',9);
COMMIT;


INSERT INTO mentor4you_db.certificats (id,description,link,name,certificats) VALUES
(1,'description','http://link','Very Cool Certificat',5),
(2,'description','http://link','Very Cool Certificat',6),
(3,'description','http://link','Very Cool Certificat',7),
(4,'description','http://link','Very Cool Certificat',8),
(5,'description','http://link','Very Cool Certificat',9),
(6,'description','http://link','Very Cool Certificat',5),
(7,'description','http://link','Very Cool Certificat',8),
(8,'description','http://link','Very Cool Certificat',9);
COMMIT;
