CREATE TABLE `auto_test`.`choice` (
  `id`          INT      NOT NULL AUTO_INCREMENT,
  `question_id` INT      NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);
