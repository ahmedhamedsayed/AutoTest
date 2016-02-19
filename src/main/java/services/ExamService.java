package services;

import java.util.ArrayList;
import java.util.List;

import constants.MainState;
import models.Exam;
import models.ExamPercentage;
import models.Question;
import models.Unit;
import repositorys.ExamPercentageRepository;
import repositorys.ExamRepository;
import ui.ExamUI;
import util.shape.InputDialog;
import util.shape.Success;
import util.shape.TimerCountDown;

public class ExamService {

	private static ExamService examService;

	public static synchronized ExamService getInstance() {
		if (examService == null)
			return examService = new ExamService();
		return examService;
	}

	private void addExamQuestion(Exam exam, Question question) {
		for (Question examQuestion : exam.getQuestions())
			if (examQuestion.getId() == question.getId())
				return;
		exam.getQuestions().add(question);
		ExamRepository.getInstance().update(exam);
	}

	private void deleteExamQuestion(Exam exam, Question question) {
		for (Question examQuestion : exam.getQuestions())
			if (examQuestion.getId() == question.getId()) {
				exam.getQuestions().remove(examQuestion);
				break;
			}
		ExamRepository.getInstance().update(exam);
	}

	public List<Exam> getAllExams() {
		return ExamRepository.getInstance().findAll();
	}

	public void refreshExamQuestion() {
		Exam exam = AdminService.getInstance().getSelectedExam();
		exam = ExamRepository.getInstance().findOneById(exam.getId());
		ExamUI.getInstance().setQuestions(exam.getQuestions());
	}

	public Question getSelectedQuestion() {
		return ExamUI.getInstance().getSelectedQuestion();
	}

	public void deleteExamsQuestion(Question question) {
		if (question != null) {
			List<Exam> exams = getAllExams();
			for (Exam exam : exams)
				deleteExamQuestion(exam, question);
			Success.reportSuccessMessage("");
		}
	}

	public void examAdd() {
		Exam exam = AdminService.getInstance().getSelectedExam();
		if (exam != null) {
			if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits)) {
				Unit unit = AdminService.getInstance().getSelectedUnit();
				if (unit != null) {
					List<Question> questions = UnitService.getInstance().getUnitQuesiton(unit);
					for (Question question : questions) {
						addExamQuestion(exam, question);
					}
					ExamRepository.getInstance().update(exam);
					Success.reportSuccessMessage("");
				}
			} else {
				Question question = AdminService.getInstance().getSelectedQuestion();
				if (question != null) {
					addExamQuestion(exam, question);
					Success.reportSuccessMessage("");
				}
			}
		}
	}

	public void examNew() {
		MainService.getInstance().nextState(MainState.AdminNewExam);
		ExamUI.getInstance().resetPanel();
	}

	public void examUpdate() {
		MainService.getInstance().nextState(MainState.AdminUpdateExam);
		Exam exam = AdminService.getInstance().getSelectedExam();
		if (exam != null) {
			exam = ExamRepository.getInstance().findOneById(exam.getId());
			ExamUI.getInstance().setExam(exam);
			ExamUI.getInstance().setExams(ExamRepository.getInstance().findAll());
		}
	}

	public void examDelete() {
		Exam exam = AdminService.getInstance().getSelectedExam();
		if (exam != null) {
			exam.setQuestions(new ArrayList<Question>());
			ExamRepository.getInstance().update(exam);
			ExamRepository.getInstance().delete(exam);
			AdminService.getInstance().refreshAdminExams();
			Success.reportSuccessMessage("");
		}
	}

	public void examOpenQuestion() {
		MainService.getInstance().nextState(MainState.AdminShowExamQuestion);
		QuestionService.getInstance().openQuestion(getSelectedQuestion(), new TimerCountDown(0, 0, 0));
	}

	public void examDeleteQuestion() {
		Exam exam = AdminService.getInstance().getSelectedExam();
		Question question = ExamUI.getInstance().getSelectedQuestion();
		if (question != null) {
			deleteExamQuestion(exam, question);
			refreshExamQuestion();
			Success.reportSuccessMessage("");
		}
	}

	public void examDone() {
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewExam)) {
			ExamRepository.getInstance().save(ExamUI.getInstance().getExam());
			Success.reportSuccessMessage("");
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateExam)) {
			ExamRepository.getInstance().update(ExamUI.getInstance().getExam());
			Success.reportSuccessMessage("");
		}
	}

	public void examBack() {
		MainService.getInstance().previousState();
		AdminService.getInstance().refreshAdminExams();
	}

	public void oldExamAdd() {
		Exam exam = AdminService.getInstance().getSelectedExam();
		Exam selectedExam = ExamUI.getInstance().getSelectedExam();
		if (selectedExam != null && exam.getId() != selectedExam.getId()) {
			for (ExamPercentage curExamPercentage : exam.getExamPercentages()) {
				if (curExamPercentage.getAnotherExamId() == selectedExam.getId()) {
					ExamPercentageRepository.getInstance().delete(curExamPercentage);
					exam.getExamPercentages().remove(curExamPercentage);
					break;
				}
			}
			double percentage = Double.valueOf(InputDialog.create("Enter Exam Percentage"));
			ExamPercentage examPercentage = new ExamPercentage();
			examPercentage.setExam(exam);
			examPercentage.setAnotherExamId(selectedExam.getId());
			examPercentage.setPercentage(percentage);
			examPercentage = ExamPercentageRepository.getInstance().save(examPercentage);
			exam.getExamPercentages().add(examPercentage);
			ExamUI.getInstance().setExam(exam);
			ExamUI.getInstance().setExams(getAllExams());
		}
	}
}
