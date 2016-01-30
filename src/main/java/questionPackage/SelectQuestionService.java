package questionPackage;

import main.MainService;
import main.MainState;
import questionPackage.question.choose.ChooseUI;
import questionPackage.question.complete.CompleteUI;
import questionPackage.question.connect.ConnectUI;
import questionPackage.question.mathproblem.MathProblemUI;
import questionPackage.question.modelproblem.ModelProblemUI;
import questionPackage.question.trueorfalse.TrueOrFalseUI;
import admin.AdminService;

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
		ChooseUI.getInstance().moveFocus();
	}

	public void newTrueOrFalse() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		//MainService.getInstance().setQuestionPanel(TrueOrFalseUI.getInstance());
		TrueOrFalseUI.getInstance().resetTextArea();
	}

	public void newComplete() {
		MainService.getInstance().nextState(MainState.AdminNewQuestion);
		//MainService.getInstance().setQuestionPanel(CompleteUI.getInstance());
		CompleteUI.getInstance().resetTextArea();
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
