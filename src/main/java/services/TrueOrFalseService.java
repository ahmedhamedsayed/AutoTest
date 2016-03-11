package services;

import models.TrueOrFalse;
import repositories.TrueOrFalseRepository;
import ui.TrueOrFalseUI;
import constants.MainState;

public class TrueOrFalseService {

	private static TrueOrFalseService TrueOrFalseService;

	public static TrueOrFalseService getInstance() {
		if (TrueOrFalseService == null)
			return TrueOrFalseService = new TrueOrFalseService();
		return TrueOrFalseService;
	}

	public void trueOrFalseDone() {
		TrueOrFalse trueOrFalse = (TrueOrFalse) TrueOrFalseUI.getInstance().convertToModel();
		trueOrFalse.setUnit(AdminService.getInstance().getSelectedUnit());
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewQuestion)) {
			TrueOrFalseRepository.getInstance().save(trueOrFalse);
			TrueOrFalseUI.getInstance().resetTextArea();
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateQuestion)) {
			TrueOrFalseRepository.getInstance().update(trueOrFalse);
		}
	}

	public void trueOrFalseBack() {
		MainService.getInstance().previousState();
	}
}