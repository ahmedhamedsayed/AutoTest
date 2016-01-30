package exam;

import java.util.List;

import main.MainService;
import main.MainState;
import questionPackage.question.Question;
import questionPackage.question.QuestionService;
import unit.Unit;
import unit.UnitService;
import util.shape.InputDialog;
import util.shape.Success;
import util.shape.TimerCountDown;
import admin.AdminService;
import configuration.uiConfiguration.Message;
import exam.examPercentage.ExamPercentage;
import exam.examPercentage.ExamPercentageRepository;

public class ExamService {

	private static ExamService examService;

	public static synchronized ExamService getInstance() {
		if (examService == null)
			return examService = new ExamService();
		return examService;
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
			ExamRepository.getInstance().deleteExamsQuestion(question);
			Success.reportSuccessMessage(Message.deleteExamQuestionSuccess);
		}
	}

	public void examAdd() {
		Exam exam = AdminService.getInstance().getSelectedExam();
		if (exam != null) {
			if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits)) {
				Unit unit = AdminService.getInstance().getSelectedUnit();
				if (unit != null) {
					List<Question> questions = UnitService.getInstance().getUnitQuesiton(unit);
					for (Question question : questions)
						ExamRepository.getInstance().addExamQuestion(exam, question);
					Success.reportSuccessMessage(Message.addExamQuestionSuccess);
				}
			} else {
				Question question = AdminService.getInstance().getSelectedQuestion();
				if (question != null) {
					ExamRepository.getInstance().addExamQuestion(exam, question);
					Success.reportSuccessMessage(Message.addExamQuestionSuccess);
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
			ExamRepository.getInstance().delete(exam);
			AdminService.getInstance().refreshAdminExams();
			Success.reportSuccessMessage(Message.deleteExamSuccess);
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
			ExamRepository.getInstance().deleteExamQuestion(exam, question);
			refreshExamQuestion();
			Success.reportSuccessMessage(Message.deleteExamQuestionSuccess);
		}
	}

	public void examDone() {
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewExam)) {
			ExamRepository.getInstance().save(ExamUI.getInstance().getExam());
			Success.reportSuccessMessage(Message.saveExamSuccess);
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateExam)) {
			ExamRepository.getInstance().update(ExamUI.getInstance().getExam());
			Success.reportSuccessMessage(Message.updateExamSuccess);
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
