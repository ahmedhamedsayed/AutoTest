CREATE TABLE `auto_test`.`mathproblem` (
  `question_id` INT      NOT NULL,
  `lawmark`     INT      NOT NULL,
  `numbermark`  INT      NOT NULL,
  `answer`      INT      NOT NULL,
  PRIMARY KEY (`question_id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);