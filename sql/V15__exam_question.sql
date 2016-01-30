CREATE TABLE `team_domain` (
  `exam_id`     INT(11) NOT NULL,
  `question_id` INT(11) NOT NULL,
  PRIMARY KEY (`exam_id`, `question_id`),
  CONSTRAINT `fk_exam_id` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  CONSTRAINT `fk_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
);