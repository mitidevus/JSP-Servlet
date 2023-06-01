use newservlet;

INSERT INTO role(code,name) VALUES('ADMIN', 'Quản trị viên');
INSERT INTO role(code,name) VALUES('USER', 'Người dùng');

INSERT INTO user(userName, password, fullName, status, roleId) VALUES('admin', '123456', 'admin', 1, 1);
INSERT INTO user(userName, password, fullName, status, roleId) VALUES('nguyenvana', '123456', 'Nguyễn Văn A', 1, 2);
INSERT INTO user(userName, password, fullName, status, roleId) VALUES('nguyenvanb', '123456', 'Nguyễn Văn B', 1, 2);
