package services;

import java.util.List;

import constants.MainState;
import constants.Message;
import models.Exam;
import models.Question;
import models.Unit;
import ui.AdminUI;
import util.shape.ConfirmMessage;
import util.shape.InputDialog;
import util.shape.TimerCountDown;

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
			Unit selectedUnit = getSelectedUnit();
			if (selectedUnit != null) {
				boolean allow = InputDialog.createToWaitPassword(Message.ASK_UNIT_PASSWORD.getValue(), selectedUnit.getPassword());
				if (allow) {
					MainService.getInstance().nextState(MainState.AdminQuestions);
					refreshAdminQuestions();
				}
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
			Unit selectedUnit = getSelectedUnit();
			if (selectedUnit != null && ConfirmMessage.confirmMessage(Message.DELETE_UNIT_CONFIRM.getValue())) {
				List<Question> questions = UnitService.getInstance().getUnitQuesiton(selectedUnit);
				for (Question question : questions)
					ExamService.getInstance().deleteExamsQuestion(question);
				UnitService.getInstance().deleteUnit(selectedUnit);
				refreshAdminUnits();
			}
		} else {
			Question selectedQuestion = getSelectedQuestion();
			if (selectedQuestion != null && ConfirmMessage.confirmMessage(Message.DELETE_QUESTION_CONFIRM.getValue())) {
				ExamService.getInstance().deleteExamsQuestion(selectedQuestion);
				QuestionService.getInstance().deleteQuestion(selectedQuestion);
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
		// if (ConfirmMessage.confirmMessage(Message.existSystemConfirm)) {
		// System.exit(0);
		// }
	}
}
