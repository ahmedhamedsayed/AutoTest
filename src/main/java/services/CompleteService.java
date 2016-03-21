package services;

import models.Complete;
import repositories.CompleteRepository;
import ui.CompleteUI;
import constants.MainState;

public class CompleteService {

	private static CompleteService completeService;

	public static synchronized CompleteService getInstance() {
		if (completeService == null)
			return completeService = new CompleteService();
		return completeService;
	}

	public void completeDone() {
		Complete complete = (Complete) CompleteUI.getInstance().convertToModel();
		complete.setUnit(AdminService.getInstance().getSelectedUnit());
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewQuestion)) {
			CompleteRepository.getInstance().save(complete);
			CompleteUI.getInstance().resetTextArea();
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateQuestion)) {
			CompleteRepository.getInstance().update(complete);
		}
	}

	public void completeBack() {
		MainService.getInstance().previousState();
	}
}
