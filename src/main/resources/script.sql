-- Drop user first if they exist
DROP USER if exists 'developer'@'%' ;

-- Now create user with prop privileges
CREATE USER 'developer'@'%' IDENTIFIED BY 'Developer@123';

GRANT ALL PRIVILEGES ON * . * TO 'developer'@'%';

-- craete data base
CREATE DATABASE `hassan` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

USE `hassan`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `first_name` varchar(45) DEFAULT NULL,
                           `last_name` varchar(45) DEFAULT NULL,
                           `email` varchar(45) DEFAULT NULL,
                           `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
                           `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `delete_date` DATETIME DEFAULT NULL,
                           PRIMARY KEY (`id`)
);



CREATE TABLE user_application (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  username VARCHAR(255) NOT NULL UNIQUE,
                                  password VARCHAR(255) NOT NULL,
                                  enabled BOOLEAN DEFAULT TRUE,
                                  UNIQUE INDEX idx_username (username)
);

CREATE TABLE roles (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       role ENUM('ROLE_USER', 'ROLE_ADMIN', 'ROLE_OTHER') NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES user_application(id) ON DELETE CASCADE
)

CREATE TABLE Token (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       token VARCHAR(255) NOT NULL,
                       expired BOOLEAN NOT NULL,
                       revoked BOOLEAN NOT NULL,
                       creation_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       user_id INT,
                       CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES UserApplication(id)
);

-- Insert Roles
INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_USER');

-- Insert Authorities
INSERT INTO authority ( name) VALUES ( 'api.students.createStudent');
INSERT INTO authority ( name) VALUES ( 'api.students.getAllStudents');
INSERT INTO authority ( name) VALUES ( 'getAllStudents.{id}.getStudentById');
INSERT INTO authority ( name) VALUES ( 'api.students.{id}.updateStudent');
INSERT INTO authority ( name) VALUES ( 'api.students.{id}.deleteStudent');
INSERT INTO authority ( name) VALUES ( 'api.students.filter.filterStudents');


INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES (1, 'hassan', 'hassani', 'hassan@example.com', '2024-10-15 12:04:50', '2024-10-22 17:35:17', NULL);
INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES (2, 'ali', 'aliii', 'ali@example.com', '2024-10-15 12:06:58', '2024-10-22 17:35:26', NULL);
INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES (3, 'ebi', 'ebii', 'ebii@example.com', '2024-10-15 12:07:08', '2024-10-22 17:35:46', NULL);
INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES (4, 'esi', 'esii', 'esiii@example.com', '2024-10-15 12:07:42', '2024-10-22 17:36:13', NULL);
INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES (5, 'ehsan', 'ehasani', 'ehsani@example.com', '2024-10-16 09:39:41', '2024-10-22 17:36:51', NULL);
INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES (6, 'akbar', 'akbari', 'akbari@example.com', '2024-10-16 09:40:30', '2024-10-22 17:37:09', NULL);
