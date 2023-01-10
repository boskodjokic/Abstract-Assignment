INSERT INTO users (id, name, password, role) VALUES
(1, 'user1', '1234', 'ADMIN'),
(2, 'user2', 'pass', 'EMPLOYEE'),
(3, 'user3', 'qwerty', 'SUPER_ADMIN'),
(4, 'user4', 'pass1234', 'EMPLOYEE'),
(5, 'user5', 'asdf', 'ADMIN');


INSERT INTO request (ip_address, path, user_id) VALUES
('100.100.100.100', '/admin/settings', 1),
('100.100.100.100', '/admin/', 2),
('100.100.100.1', '/admin/', 3),
('100.100.100.100', '/admin/', 4),
('100.100.100.101', '/admin/', 5);