CREATE TABLE `auto_test`.`trueorfalse` (
  `question_id` INT      NOT NULL,
  `mark`        INT      NOT NULL,
  `answer`      LONGTEXT NOT NULL,
  PRIMARY KEY (`question_id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);