INSERT INTO users (name, surname, uuid) VALUES ("name", "surname", "38596d06-534a-46c8-b625-ccef90359ee6");
INSERT INTO users (name, surname, uuid) VALUES ("name1",	"surname1", "9cebf1c5-5615-4e22-b860-8ed4722a215d");


INSERT INTO auth_users (login, password, status, created, updated) VALUES ("login",
"$2a$10$pigBySqDFbVFdG27l6QHROvDWWQuNyBNjVK9NIx2vSzI6.seaEd0e",	"ACTIVE",	'2021-01-27 12:12:50', '2021-01-27 12:12:50');

INSERT INTO auth_users (login, password, status, created, updated) VALUES ("login1",
"$2a$10$Bqg2EWWpgaUIOrMhDaXiAenCN4M3MJPPd7JPDLs4HnMj7g7K.jQxi",	"ACTIVE",	'2021-01-27 12:13:57', '2021-01-27 12:13:57');


INSERT INTO roles (name) VALUES ("ROLE_ADMIN");
INSERT INTO roles (name) VALUES ("ROLE_USER");


INSERT INTO auth_user_roles (auth_user_id, role_id) VALUES (1, 1);
INSERT INTO auth_user_roles (auth_user_id, role_id) VALUES (1, 2);
INSERT INTO auth_user_roles (auth_user_id, role_id) VALUES (2, 2);

