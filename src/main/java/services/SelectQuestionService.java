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

	public void newTrueOrFalse() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		TrueOrFalseUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(TrueOrFalseUI.getInstance().getPanel());
	}

	public void newComplete() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		TrueOrFalseUI.getInstance().resetTextArea();
		MainService.getInstance().setQuestionPanel(CompleteUI.getInstance().getPanel());
	}

	public void newConnect() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		//MainService.getInstance().setQuestionPanel(ConnectUI.getInstance());
		ConnectUI.getInstance().resetTextArea();
	}

	public void newMathProblem() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		//MainService.getInstance().setQuestionPanel(MathProblemUI.getInstance());
		MathProblemUI.getInstance().resetTextArea();
	}

	public void newModelProblem() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		//MainService.getInstance().setQuestionPanel(ModelProblemUI.getInstance());
		ModelProblemUI.getInstance().resetTextArea();
	}

	public void newBack() {
		MainService.getInstance().previousState();
		AdminService.getInstance().refreshAdminQuestions();
	}
}
