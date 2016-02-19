package unit;

import java.util.ArrayList;
import java.util.List;

import main.MainService;
import questionPackage.question.Question;
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
		try {
			return UnitRepository.getInstance().findAll();
		} catch (Exception e) {
			Error.reportErrorMessage("");
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
		String newPassword = InputDialog.create("Enter Unit Password");
		if (newName != null && newPassword != null && !newName.trim().isEmpty() && !newPassword.trim().isEmpty()) {
			Unit unit = new Unit();
			unit.setDescription(newName);
			unit.setPassword(newPassword);
			UnitRepository.getInstance().save(unit);
			Success.reportSuccessMessage("");
		}
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
				Success.reportSuccessMessage("");
			}
		}
	}

	public void deleteUnit(Unit unit) {
		String password = InputDialog.create("Enter Unit Password");
		if (unit != null && unit.getPassword().equals(password)) {
			UnitRepository.getInstance().delete(unit);
			Success.reportSuccessMessage("");
		}
	}
}
