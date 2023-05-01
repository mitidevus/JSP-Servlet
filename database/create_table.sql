use newservlet;

CREATE TABLE role (
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NULL,
    updatedAt TIMESTAMP NULL,
    createdBy VARCHAR(255) NULL,
    updatedBy VARCHAR(255) NULL
);

CREATE TABLE user (
	id bigint NOT NULL PRIMARY KEY auto_increment,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
	fullName VARCHAR(255) NOT NULL,
    status int NOT NULL,
	roleId bigint NOT NULL,
    createdAt TIMESTAMP NULL,
    updatedAt TIMESTAMP NULL,
    createdBy VARCHAR(255) NULL,
    updatedBy VARCHAR(255) NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES role(id);

CREATE TABLE news (
	id bigint NOT NULL PRIMARY KEY auto_increment,
    title VARCHAR(255) NULL,
    thumbnail VARCHAR(255) NULL,
    shortDescription TEXT NULL,
	content TEXT NULL,
    categoryId bigint NOT NULL,
    createdAt TIMESTAMP NULL,
    updatedAt TIMESTAMP NULL,
    createdBy VARCHAR(255) NULL,
    updatedBy VARCHAR(255) NULL
);

CREATE TABLE category (
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NULL,
    updatedAt TIMESTAMP NULL,
    createdBy VARCHAR(255) NULL,
    updatedBy VARCHAR(255) NULL
);

ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryId) REFERENCES category(id);

CREATE TABLE comment (
	id bigint NOT NULL PRIMARY KEY auto_increment,
	content TEXT NOT NULL,
    userId bigint NOT NULL,
    newsId bigint NOT NULL,
    createdAt TIMESTAMP NULL,
    updatedAt TIMESTAMP NULL,
    createdBy VARCHAR(255) NULL,
    updatedBy VARCHAR(255) NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (userId) REFERENCES user(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_news FOREIGN KEY (newsId) REFERENCES news(id);
