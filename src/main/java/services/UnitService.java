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

	private String getUnitDescription(int id) {
		String newDescription;
		while (true) {
			newDescription = InputDialog.createToWaitValue(Message.ASK_UNIT_DESCRIPTION.getValue(), Message.UNIT_DESCRIPTION_INVALID_ERROR.getValue());
			if (newDescription == null)
				return null;
			Unit unitDescriptionExist = UnitRepository.getInstance().findOneByDescription(newDescription);
			if (unitDescriptionExist == null || id == unitDescriptionExist.getId())
				break;
			Error.reportErrorMessage(Message.UNIT_DESCRIPTION_ALLREADY_EXIST_ERROR.getValue());
		}
		return newDescription;
	}

	private String getUnitPassword(int id) {
		String newPassword;
		while (true) {
			newPassword = InputDialog.createToWaitValue(Message.ASK_UNIT_NEW_PASSWORD.getValue(), Message.UNIT_PASSWORD_INVALID_ERROR.getValue());
			if (newPassword == null)
				return null;
			Unit unitPasswordExist = UnitRepository.getInstance().findOneByPassword(newPassword);
			if (unitPasswordExist == null || id == unitPasswordExist.getId())
				break;
			Error.reportErrorMessage(Message.UNIT_PASSWORD_ALLREADY_EXIST_ERROR.getValue());
		}
		return newPassword;
	}

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
		String newDescription = getUnitDescription(0);
		if (newDescription == null)
			return;
		String newPassword = getUnitPassword(0);
		if (newPassword == null)
			return;
		Unit unit = new Unit();
		unit.setDescription(newDescription);
		unit.setPassword(newPassword);
		unit = UnitRepository.getInstance().save(unit);
		Success.reportSuccessMessage(Message.UNIT_SAVED_SUCCESSFULLY.getValue());
	}

	public void updateUnit(Unit unit) {
		String newDescription = getUnitDescription(unit.getId());
		if (newDescription == null)
			return;
		String newPassword = getUnitPassword(unit.getId());
		if (newPassword == null)
			return;
		unit.setDescription(newDescription);
		unit.setPassword(newPassword);
		UnitRepository.getInstance().update(unit);
		Success.reportSuccessMessage(Message.UNIT_UPDATED_SUCCESSFULLY.getValue());
	}

	public void deleteUnit(Unit unit) {
		for (Question question : unit.getQuestions())
			QuestionService.getInstance().deleteQuestion(question);
		UnitRepository.getInstance().delete(unit);
		Success.reportSuccessMessage(Message.DELETE_UNIT_SUCCESSFULLY.getValue());
	}
}
