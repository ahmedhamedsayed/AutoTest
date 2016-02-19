package admin;

import java.util.List;

import main.MainService;
import main.MainState;
import questionPackage.question.Question;
import questionPackage.question.QuestionService;
import unit.Unit;
import unit.UnitService;
import util.shape.ConfirmMessage;
import util.shape.InputDialog;
import util.shape.TimerCountDown;
import exam.Exam;
import exam.ExamService;

public class AdminService {

	private static AdminService adminService;

	public static synchronized AdminService getInstance() {
		if (adminService == null)
			return adminService = new AdminService();
		return adminService;
	}

	public void refreshAdminUnits() {
		AdminUI.getInstance().setUnits(UnitService.getInstance().getAllUnits());
	}

	public void refreshAdminQuestions() {
		MainService.getInstance().nextState(MainState.AdminQuestions);
		AdminUI.getInstance().setQuestions(UnitService.getInstance().getUnitQuesiton(getSelectedUnit()));
	}

	public void refreshAdminExams() {
		AdminUI.getInstance().setExams(ExamService.getInstance().getAllExams());
	}

	public Unit getSelectedUnit() {
		return AdminUI.getInstance().getSelectedUnit();
	}

	public Exam getSelectedExam() {
		return AdminUI.getInstance().getSelectedExam();
	}

	public Question getSelectedQuestion() {
		return AdminUI.getInstance().getSelectedQuestion();
	}

	public void openAdmin() {
		MainService.getInstance().nextState(MainState.AdminUnits);
		refreshAdminUnits();
		refreshAdminExams();
	}

	public void adminNext() {
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits)) {
			String password = InputDialog.create("Enter Old Unit Password");
			if (getSelectedUnit() != null && getSelectedUnit().getPassword().equals(password)) {
				refreshAdminQuestions();
			}
		} else {
			MainService.getInstance().nextState(MainState.AdminShowQuestion);
			QuestionService.getInstance().openQuestion(getSelectedQuestion(), new TimerCountDown(0, 0, 0));
		}
	}

	public void adminNew() {
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits)) {
			UnitService.getInstance().addUnit();
			refreshAdminUnits();
		} else {
			MainService.getInstance().nextState(MainState.AdminSelectQuestion);
		}
	}

	public void adminUpdate() {
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits)) {
			UnitService.getInstance().updateUnit(getSelectedUnit());
			refreshAdminUnits();
		} else {
			MainService.getInstance().nextState(MainState.AdminUpdateQuestion);
			QuestionService.getInstance().openQuestion(getSelectedQuestion(), new TimerCountDown(0, 0, 0));
		}
	}

	public void adminDelete() {
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits)) {
			if (ConfirmMessage.confirmMessage(""/*Message.deleteUnitConfirm*/)) {
				List<Question> questions = UnitService.getInstance().getUnitQuesiton(getSelectedUnit());
				for (Question question : questions) {
					ExamService.getInstance().deleteExamsQuestion(question);
				}
				UnitService.getInstance().deleteUnit(getSelectedUnit());
				refreshAdminUnits();
			}
		} else {
			if (ConfirmMessage.confirmMessage(""/*Message.deleteQuestionConfirm*/)) {
				ExamService.getInstance().deleteExamsQuestion(getSelectedQuestion());
				QuestionService.getInstance().deleteQuestion(getSelectedQuestion());
				refreshAdminQuestions();
			}
		}
	}

	public void adminPrev() {
		MainService.getInstance().previousState();
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminUnits))
			refreshAdminUnits();
	}

	public void adminOut() {
//		if (ConfirmMessage.confirmMessage(Message.existSystemConfirm)) {
//			System.exit(0);
//		}
	}
}
