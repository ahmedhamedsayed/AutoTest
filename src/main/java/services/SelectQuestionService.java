package services;

import constants.MainState;
import ui.ChooseUI;
import ui.CompleteUI;
import ui.ConnectUI;
import ui.MathProblemUI;
import ui.ModelProblemUI;
import ui.TrueOrFalseUI;

public class SelectQuestionService {

	private static SelectQuestionService selectQuestionService;

	public static SelectQuestionService getInstance() {
		if (selectQuestionService == null)
			return selectQuestionService = new SelectQuestionService();
		return selectQuestionService;
	}

	public void newChoose() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		ChooseUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(ChooseUI.getInstance().getPanel());
	}

	public void newComplete() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		CompleteUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(CompleteUI.getInstance().getPanel());
	}

	public void newConnect() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		ConnectUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(ConnectUI.getInstance().getPanel());
	}

	public void newMathProblem() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		MathProblemUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(MathProblemUI.getInstance().getPanel());
	}

	public void newModelProblem() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		ModelProblemUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(ModelProblemUI.getInstance().getPanel());
	}

	public void newTrueOrFalse() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		TrueOrFalseUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(TrueOrFalseUI.getInstance().getPanel());
	}

	public void newBack() {
		MainService.getInstance().previousState();
		AdminService.getInstance().refreshAdminQuestions();
	}
}
