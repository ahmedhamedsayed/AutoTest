package questionPackage.question;

import main.MainService;
import main.MainState;
import util.shape.Success;
import util.shape.TimerCountDown;
import configuration.uiConfiguration.Message;

public class QuestionService {

	private static QuestionService questionService;

	public static QuestionService getInstance() {
		if (questionService == null) {
			return questionService = new QuestionService();
		}
		return questionService;
	}

	public boolean isAdminEdit() {
		MainState mainState = MainService.getInstance().getCurrentState();
		return mainState.equals(MainState.AdminNewQuestion) || mainState.equals(MainState.AdminUpdateQuestion);
	}

	public boolean isAdminShow() {
		MainState mainState = MainService.getInstance().getCurrentState();
		return mainState.equals(MainState.AdminShowQuestion) || mainState.equals(MainState.AdminShowExamQuestion);
	}
	
	public boolean isStudent() {
		MainState mainState = MainService.getInstance().getCurrentState();
		return mainState.equals(MainState.StudentShowQuestion);
	}
	
	public void openQuestion(Question question, TimerCountDown timerCountDown) {
		question = question.getQuestionRepository().findOneById(question.getId());
		QuestionUI questionUI = question.getQuestionUI();
		if (timerCountDown != null)
			questionUI.setTimerCountDown(timerCountDown);
		questionUI.resetTextArea();
		questionUI.convertToUI(question);
		MainService.getInstance().setQuestionPanel(questionUI.getPanel());
		questionUI.moveFocus();
	}

	public void deleteQuestion(Question question) {
		question.getQuestionRepository().delete(question);
		Success.reportSuccessMessage(Message.deleteQuestionSuccess);
	}
}
