package services;

import constants.Message;
import util.shape.ConfirmMessage;
import util.shape.InputDialog;

public class LoginService {

	private static LoginService loginService;

	public static LoginService getInstance() {
		if (loginService == null)
			return loginService = new LoginService();
		return loginService;
	}

	public void loginAdmin() {
		boolean allow = InputDialog.createToWaitPassword(Message.ASK_ADMIN_PASSWORD.getValue(), Message.ADMIN_PASSWORD.getValue());
		if (allow)
			AdminService.getInstance().openAdmin();
	}

	public void loginTeacher() {
		boolean allow = InputDialog.createToWaitPassword(Message.ASK_TEACHER_PASSWORD.getValue(), Message.TEACHER_PASSWORD.getValue());
		if (allow)
			TeacherService.getInstance().generateFinalSheet();
	}

	public void loginUser() {
		String password = InputDialog.create(Message.ASK_EXAM_PASSWORD.getValue());
		if (password != null)
			ExamExecuteService.getInstance().executeExam(password);
	}

	public void loginOut() {
		if (ConfirmMessage.confirmMessage(Message.EXIST_SYSTEM_CONFIRM_MESSAGE.getValue())) {
			System.exit(0);
		}
	}
}
