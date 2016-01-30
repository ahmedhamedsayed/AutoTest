CREATE TABLE `auto_test`.`question` (
  `id`          INT      NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NOT NULL,
  `unit_id`     INT      NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`unit_id`) REFERENCES unit (id)
);
