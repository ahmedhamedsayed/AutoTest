package services;

import models.MathProblem;
import repositories.MathProblemRepository;
import ui.MathProblemUI;
import constants.MainState;

public class MathProblemService {

	private static MathProblemService mathProblemService;

	public static synchronized MathProblemService getInstance() {
		if (mathProblemService == null)
			return mathProblemService = new MathProblemService();
		return mathProblemService;
	}

	public void mathProblemDone() {
		MathProblem mathProblem = (MathProblem) MathProblemUI.getInstance().convertToModel();
		mathProblem.setUnit(AdminService.getInstance().getSelectedUnit());
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewQuestion)) {
			MathProblemRepository.getInstance().save(mathProblem);
			MathProblemUI.getInstance().resetTextArea();
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateQuestion)) {
			MathProblemRepository.getInstance().update(mathProblem);
		}
	}

	public void mathProblemBack() {
		MainService.getInstance().previousState();
	}
}
