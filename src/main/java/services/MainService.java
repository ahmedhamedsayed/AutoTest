package services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.AdminUI;
import ui.ExamUI;
import ui.LoginUI;
import ui.SelectQuestionUI;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.MainState;
import constants.Message;

public class MainService extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainService mainService;
	private List<MainState> mainStates;
	private JPanel[] panels;

	public MainService() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static synchronized MainService getInstance() {
		if (mainService == null)
			return mainService = new MainService();
		return mainService;
	}

	public void initialize() {
		panels = new JPanel[5];
		panels[0] = LoginUI.getInstance().getPanel();
		panels[1] = AdminUI.getInstance().getPanel();
		panels[2] = SelectQuestionUI.getInstance().getQuestionPanel();
		panels[3] = ExamUI.getInstance().getPanel();
		panels[4] = new JPanel();
		for (JPanel panel : panels)
			this.add(panel);
		mainStates = new ArrayList<MainState>();
		nextState(MainState.Login);
		DatabaseEngine.getInstance().configureDatabase("persistence.xml", Message.MAIN_DATABASE_PATH.getValue());
		DatabaseEngine.getInstance().configureDatabase("studentPersistence.xml", Message.STUDENT_DATABASE_PATH.getValue());
	}

	public MainState getCurrentState() {
		return mainStates.get(mainStates.size() - 1);
	}

	public void nextState(MainState mainState) {
		if (mainStates.size() == 0 || !mainStates.get(mainStates.size() - 1).equals(mainState)) {
			mainStates.add(mainState);
		}
		changeState();
	}

	public void previousState() {
		mainStates.remove(mainStates.size() - 1);
		changeState();
	}

	private void changeState() {
		for (JPanel panel : panels)
			panel.setVisible(false);
		MainState currentMainState = getCurrentState();
		if (currentMainState.equals(MainState.Login)) {
			panels[0].setVisible(true);
			LoginUI.getInstance().resetFocus();
		} else if (currentMainState.equals(MainState.AdminUnits)) {
			panels[1].setVisible(true);
			AdminUI.getInstance().resetUnitFocus();
		} else if (currentMainState.equals(MainState.AdminQuestions)) {
			panels[1].setVisible(true);
			AdminUI.getInstance().resetQuestionFocus();
		} else if (currentMainState.equals(MainState.AdminSelectQuestion)) {
			panels[2].setVisible(true);
			SelectQuestionUI.getInstance().resetFocus();
		} else if (currentMainState.equals(MainState.AdminNewExam)) {
			panels[3].setVisible(true);
			ExamUI.getInstance().resetFocus();
		} else if (currentMainState.equals(MainState.AdminUpdateExam)) {
			panels[3].setVisible(true);
			ExamUI.getInstance().resetFocus();
		} else {
			panels[4].setVisible(true);
		}
	}

	public void setQuestionPanel(JPanel panel) {
		panels[4] = panel;
		this.add(panels[4]);
	}

	public void loginAdmin() {
		LoginService.getInstance().loginAdmin();
	}

	public void loginTeacher() {
		LoginService.getInstance().loginTeacher();
	}

	public void loginUser() {
		LoginService.getInstance().loginUser();
	}

	public void loginOut() {
		LoginService.getInstance().loginOut();
	}

	public void adminNext() {
		AdminService.getInstance().adminNext();
	}

	public void adminNew() {
		AdminService.getInstance().adminNew();
	}

	public void adminUpdate() {
		AdminService.getInstance().adminUpdate();
	}

	public void adminDelete() {
		AdminService.getInstance().adminDelete();
	}

	public void adminPrev() {
		AdminService.getInstance().adminPrev();
	}

	public void adminOut() {
		AdminService.getInstance().adminOut();
	}

	public void examAdd() {
		ExamService.getInstance().examAdd();
	}

	public void examNew() {
		ExamService.getInstance().examNew();
	}

	public void examUpdate() {
		ExamService.getInstance().examUpdate();
	}

	public void examDelete() {
		ExamService.getInstance().examDelete();
	}

	public void examOpenQuestion() {
		ExamService.getInstance().examOpenQuestion();
	}

	public void examDeleteQuestion() {
		ExamService.getInstance().examDeleteQuestion();
	}

	public void examDone() {
		ExamService.getInstance().examDone();
	}

	public void examBack() {
		ExamService.getInstance().examBack();
	}

	public void oldExamAdd() {
		ExamService.getInstance().oldExamAdd();
	}

	public void newChoose() {
		SelectQuestionService.getInstance().newChoose();
	}

	public void newTrueOrFalse() {
		SelectQuestionService.getInstance().newTrueOrFalse();
	}

	public void newComplete() {
		SelectQuestionService.getInstance().newComplete();
	}

	public void newConnect() {
		SelectQuestionService.getInstance().newConnect();
	}

	public void newMathProblem() {
		SelectQuestionService.getInstance().newMathProblem();
	}

	public void newModelProblem() {
		SelectQuestionService.getInstance().newModelProblem();
	}

	public void newBack() {
		SelectQuestionService.getInstance().newBack();
	}

	public void chooseDone() {
		ChooseService.getInstance().chooseDone();
	}

	public void chooseBack() {
		ChooseService.getInstance().chooseBack();
	}

	public void trueOrFalseDone() {
		TrueOrFalseService.getInstance().trueOrFalseDone();
	}

	public void trueOrFalseBack() {
		TrueOrFalseService.getInstance().trueOrFalseBack();
	}

	public void next() {
		ExamExecuteService.getInstance().moveNext();
	}

	public void exit() {
		ExamExecuteService.getInstance().exit();
	}
}
