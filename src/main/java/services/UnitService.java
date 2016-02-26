package services;

import java.util.List;

import constants.Message;
import models.Question;
import models.Unit;
import repositories.UnitRepository;
import util.shape.Error;
import util.shape.InputDialog;
import util.shape.Success;

public class UnitService {

	private static UnitService unitService;

	public static UnitService getInstance() {
		if (unitService == null)
			return unitService = new UnitService();
		return unitService;
	}

	public List<Unit> getAllUnits() {
		return UnitRepository.getInstance().findAll();
	}

	public List<Question> getUnitQuesiton(Unit unit) {
		return UnitRepository.getInstance().findOneById(unit.getId()).getQuestions();
	}

	public void addUnit() {
		String newDescription = "", newPassword = "";
		while (true) {
			newDescription = InputDialog.createToWaitValue(Message.ASK_UNIT_DESCRIPTION.getValue(), Message.UNIT_DESCRIPTION_INVALID_ERROR.getValue());
			if (newDescription == null)
				return;
			Unit unitDescriptionExist = UnitRepository.getInstance().findOneByDescription(newDescription);
			if (unitDescriptionExist == null)
				break;
			Error.reportErrorMessage(Message.UNIT_DESCRIPTION_ALLREADY_EXIST_ERROR.getValue());
		}
		while (true) {
			newPassword = InputDialog.createToWaitValue(Message.ASK_UNIT_NEW_PASSWORD.getValue(), Message.UNIT_PASSWORD_INVALID_ERROR.getValue());
			if (newPassword == null)
				return;
			Unit unitPasswordExist = UnitRepository.getInstance().findOneByPassword(newPassword);
			if (unitPasswordExist == null)
				break;
			Error.reportErrorMessage(Message.UNIT_PASSWORD_ALLREADY_EXIST_ERROR.getValue());
		}
		Unit unit = new Unit();
		unit.setDescription(newDescription);
		unit.setPassword(newPassword);
		unit = UnitRepository.getInstance().save(unit);
		Success.reportSuccessMessage(Message.UNIT_SAVED_SUCCESSFULLY.getValue());
	}

	public void updateUnit(Unit unit) {
		String password = InputDialog.create("Enter Old Unit Password");
		if (unit != null && unit.getPassword().equals(password)) {
			String newName = InputDialog.create("Enter Unit Name");
			String newPassword = InputDialog.create("Enter New Unit Password");
			if (newName != null && newPassword != null && !newName.trim().isEmpty() && !newPassword.trim().isEmpty()) {
				unit.setDescription(newName);
				unit.setPassword(newPassword);
				UnitRepository.getInstance().update(unit);
				if (unit != null)
					Success.reportSuccessMessage(Message.UNIT_UPDATED_SUCCESSFULLY.getValue());
			}
		}
	}

	public void deleteUnit(Unit unit) {
		String password = InputDialog.create("Enter Unit Password");
		if (unit != null && unit.getPassword().equals(password)) {
			for (Question question : unit.getQuestions())
				QuestionService.getInstance().deleteQuestion(question);
			UnitRepository.getInstance().delete(unit);
			Success.reportSuccessMessage("");
		}
	}
}
