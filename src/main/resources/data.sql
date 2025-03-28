/*

-- create data Base
DROP DATABASE IF EXISTS hassan;
CREATE DATABASE hassan CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

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

INSERT INTO `student` ( `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES ( 'hassan', 'hassani', 'hassan@example.com', '2024-10-15 12:04:50', '2024-10-22 17:35:17', NULL);
INSERT INTO `student` ( `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES ( 'ali', 'aliii', 'ali@example.com', '2024-10-15 12:06:58', '2024-10-22 17:35:26', NULL);
INSERT INTO `student` (`first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES ( 'ebi', 'ebii', 'ebii@example.com', '2024-10-15 12:07:08', '2024-10-22 17:35:46', NULL);
INSERT INTO `student` ( `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES ('esi', 'esii', 'esiii@example.com', '2024-10-15 12:07:42', '2024-10-22 17:36:13', NULL);
INSERT INTO `student` ( `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES ('ehsan', 'ehasani', 'ehsani@example.com', '2024-10-16 09:39:41', '2024-10-22 17:36:51', NULL);
INSERT INTO `student` ( `first_name`, `last_name`, `email`, `create_date`, `last_modified_date`, `delete_date`) VALUES ( 'akbar', 'akbari', 'akbari@example.com', '2024-10-16 09:40:30', '2024-10-22 17:37:09', NULL);*/