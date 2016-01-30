package unit;

import java.util.ArrayList;
import java.util.List;

import main.MainService;
import questionPackage.question.Question;
import util.shape.Error;
import util.shape.InputDialog;
import util.shape.Success;
import configuration.uiConfiguration.Message;

public class UnitService {

	private static UnitService unitService;

	public static UnitService getInstance() {
		if (unitService == null)
			return unitService = new UnitService();
		return unitService;
	}

	public List<Unit> getAllUnits() {
		try {
			return UnitRepository.getInstance().findAll();
		} catch (Exception e) {
			Error.reportErrorMessage(Message.loadAllUnitsError);
			System.exit(0);
			return null;
		}
	}

	public List<Question> getUnitQuesiton(Unit unit) {
		if (unit != null) {
			return UnitRepository.getInstance().findOneById(unit.getId()).getQuestions();
		} else {
			MainService.getInstance().previousState();
			return new ArrayList<Question>();
		}
	}

	public void addUnit() {
		String newName = InputDialog.create("Enter Unit Name");
		if (newName != null) {
			Unit unit = new Unit();
			unit.setDescription(newName);
			UnitRepository.getInstance().save(unit);
			Success.reportSuccessMessage(Message.saveUnitSuccess);
		}
	}

	public void updateUnit(Unit unit) {
		if (unit != null) {
			String newName = InputDialog.create("Enter Unit Name");
			if (newName != null) {
				unit.setDescription(newName);
				UnitRepository.getInstance().update(unit);
				Success.reportSuccessMessage(Message.updateUnitSuccess);
			}
		}
	}

	public void deleteUnit(Unit unit) {
		if (unit != null) {
			UnitRepository.getInstance().delete(unit);
			Success.reportSuccessMessage(Message.deleteUnitSuccess);
		}
	}
}
