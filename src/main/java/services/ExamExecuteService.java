package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.NumberUtils;

import constants.MainState;
import constants.Message;
import models.Exam;
import models.ExamPercentage;
import models.Question;
import models.Student;
import repositories.ExamRepository;
import repositories.StudentRepository;
import util.shape.Error;
import util.shape.InputDialog;
import util.shape.TimerCountDown;

public class ExamExecuteService {

	private static ExamExecuteService examExecuteService;
	private List<Question> questions;
	private Integer examMark, examId, totalStudentMark, index;
	private TimerCountDown timerCountDown;
	private String studentId;

	public static synchronized ExamExecuteService getInstance() {
		if (examExecuteService == null)
			return examExecuteService = new ExamExecuteService();
		return examExecuteService;
	}

	private Exam findExamByPassword(String password) {
		List<Exam> exams = ExamService.getInstance().getAllExams();
		for (Exam exam : exams)
			if (exam.getPassword().equals(password))
				return exam;
		return null;
	}

	private void startExam(Exam exam, List<Question> questions) {
		MainService.getInstance().nextState(MainState.StudentShowQuestion);
		this.questions = questions;
		this.examId = exam.getId();
		this.examMark = exam.getMark();
		this.totalStudentMark = 0;
		this.timerCountDown = new TimerCountDown(exam.getHour(), exam.getMinute(), 0);
		this.index = 0;
		QuestionService.getInstance().openQuestion(this.questions.get(this.index), this.timerCountDown);
	}

	private void saveResult() {
		int totalQuestionsMark = 0;
		for (Question question : questions)
			totalQuestionsMark += question.getTotalQuestionMark();
		int totalMark = (int) Math.round((totalStudentMark * examMark) / totalQuestionsMark);
		Student student = new Student();
		student.setStudentId(studentId);
		student.setExamId(examId);
		student.setMark(totalMark);
		StudentRepository.getInstance().save(student);
	}

	private void updateResult() {
		totalStudentMark += questions.get(index).getQuestionUI().getMark();
	}

	public int useExam(List<Question> questions, int examId, int questionsNumber) {
		Exam exam = ExamRepository.getInstance().findOneById(examId);
		int questionsTaken = Math.min(questionsNumber, exam.getQuestions().size());
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < exam.getQuestions().size(); ++i)
			indexes.add(i);
		Random random = new Random();
		while (!indexes.isEmpty() && questionsNumber != 0) {
			int index = random.nextInt(indexes.size());
			questions.add(exam.getQuestions().get(indexes.get(index)));
			indexes.remove(index);
			--questionsNumber;
		}
		return questionsTaken;
	}

	public void executeExam(String password) {
		Exam exam = findExamByPassword(password);
		if (exam == null) {
			Error.reportErrorMessage(Message.PASSWORD_WRONG.getValue());
			return;
		}
		while (true) {
			studentId = InputDialog.create(Message.ASK_STUDENT_ID.getValue());
			if (studentId == null)
				return;
			if (studentId.trim().isEmpty() || !NumberUtils.isNumber(studentId))
				Error.reportErrorMessage(Message.STUDENT_ID_NOT_NUMBER_ERROR.getValue());
			else
				break;
		}
		int questionsNumber = exam.getQuestionsNumber(), questionsTaken = 0;
		List<Question> questions = new ArrayList<Question>();
		for (ExamPercentage examPercentage : exam.getExamPercentages()) {
			int currentQuestionsNumber = (int) Math.round(questionsNumber * (examPercentage.getPercentage() / 100.0));
			questionsTaken += useExam(questions, examPercentage.getAnotherExamId(), currentQuestionsNumber);
		}
		questionsTaken += useExam(questions, exam.getId(), questionsNumber - questionsTaken);
		startExam(exam, questions);
	}

	public boolean isInEnd() {
		return index == questions.size() - 1;
	}

	public void moveNext() {
		updateResult();
		++index;
		QuestionService.getInstance().openQuestion(questions.get(index), timerCountDown);
	}

	public void exit() {
		updateResult();
		timerCountDown.stopTimer();
		saveResult();
		MainService.getInstance().previousState();
	}
}
