package services;

import constants.Message;
import util.shape.ConfirmMessage;
import util.shape.Error;
import util.shape.InputDialog;

public class LoginService {

	private static LoginService loginService;

	public static LoginService getInstance() {
		if (loginService == null)
			return loginService = new LoginService();
		return loginService;
	}

	public void loginAdmin() {
		while (true) {
			String password = InputDialog.create(Message.ASK_ADMIN_PASSWORD.getValue());
			if (password == null) {
				break;
			}
			if (Message.ADMIN_PASSWORD.getValue().equals(password)) {
				AdminService.getInstance().openAdmin();
				break;
			} else {
				Error.reportErrorMessage(Message.PASSWORDWRONG.getValue());
			}
		}
	}

	public void loginTeacher() {
		while (true) {
			String password = InputDialog.create(Message.ASK_TEACHER_PASSWORD.getValue());
			if (password == null) {
				break;
			}
			if (Message.TEACHER_PASSWORD.getValue().equals(password)) {
				TeacherService.getInstance().generateFinalSheet();
				break;
			} else {
				Error.reportErrorMessage(Message.PASSWORDWRONG.getValue());
			}
		}
	}

	public void loginUser() {
		String password = InputDialog.create("Enter Exam Password");
		if (password != null)
			ExamExecuteService.getInstance().executeExam(password);
	}

	public void loginOut() {
		if (ConfirmMessage.confirmMessage(""/*Message.existSystemConfirm*/)) {
			System.exit(0);
		}
	}
}
