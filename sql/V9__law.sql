CREATE TABLE `auto_test`.`law` (
  `id`          INT      NOT NULL AUTO_INCREMENT,
  `question_id` INT      NOT NULL,
  `description` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);
