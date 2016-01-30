CREATE TABLE `auto_test`.`lawanswer` (
  `id`          INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NOT NULL,
  `description` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);
