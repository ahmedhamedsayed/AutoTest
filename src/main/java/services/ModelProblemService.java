package services;

import models.ModelProblem;
import repositories.ModelProblemRepository;
import ui.ModelProblemUI;
import constants.MainState;

public class ModelProblemService {

	private static ModelProblemService modelProblemService;

	public static synchronized ModelProblemService getInstance() {
		if (modelProblemService == null)
			return modelProblemService = new ModelProblemService();
		return modelProblemService;
	}

	public void modelProblemDone() {
		ModelProblem modelProblem = (ModelProblem) ModelProblemUI.getInstance().convertToModel();
		modelProblem.setUnit(AdminService.getInstance().getSelectedUnit());
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewQuestion)) {
			ModelProblemRepository.getInstance().save(modelProblem);
			ModelProblemUI.getInstance().resetTextArea();
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateQuestion)) {
			ModelProblemRepository.getInstance().update(modelProblem);
		}
	}

	public void modelProblemBack() {
		MainService.getInstance().previousState();
	}
}
