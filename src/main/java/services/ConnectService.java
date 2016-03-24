package services;

import models.Connect;
import repositories.ConnectRepository;
import ui.ConnectUI;
import constants.MainState;

public class ConnectService {

	private static ConnectService connectService;

	public static synchronized ConnectService getInstance() {
		if (connectService == null)
			return connectService = new ConnectService();
		return connectService;
	}

	public void connectDone() {
		Connect connect = (Connect) ConnectUI.getInstance().convertToModel();
		connect.setUnit(AdminService.getInstance().getSelectedUnit());
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminNewQuestion)) {
			ConnectRepository.getInstance().save(connect);
			ConnectUI.getInstance().resetTextArea();
		} else if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateQuestion)) {
			ConnectRepository.getInstance().update(connect);
		}
	}

	public void connectBack() {
		MainService.getInstance().previousState();
	}
}
