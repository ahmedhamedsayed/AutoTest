CREATE TABLE `auto_test`.`modelproblem` (
  `question_id` INT NOT NULL,
  `mark`        INT NOT NULL,
  PRIMARY KEY (`question_id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);