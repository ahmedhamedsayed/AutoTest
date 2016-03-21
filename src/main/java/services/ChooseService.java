package services;

import models.Choose;
import repositories.ChooseRepository;
import ui.ChooseUI;
import constants.MainState;

public class ChooseService {

	private static ChooseService chooseService;

	public static synchronized ChooseService getInstance() {
		if (chooseService == null)
			return chooseService = new ChooseService();
		return chooseService;
	}

	public void chooseDone() {
		Choose choose = (Choose) ChooseUI.getInstance().convertToModel();
		choose.setUnit(AdminService.getInstance().getSelectedUnit());
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewQuestion)) {
			ChooseRepository.getInstance().save(choose);
			ChooseUI.getInstance().resetTextArea();
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateQuestion)) {
			ChooseRepository.getInstance().update(choose);
		}
	}

	public void chooseBack() {
		MainService.getInstance().previousState();
	}
}
