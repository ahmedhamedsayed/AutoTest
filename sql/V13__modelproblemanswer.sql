CREATE TABLE `auto_test`.`modelproblemanswer` (
  `id`          INT      NOT NULL AUTO_INCREMENT,
  `question_id` INT      NOT NULL,
  `row`         INT      NOT NULL,
  `answer`      LONGTEXT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES question (id)
);
