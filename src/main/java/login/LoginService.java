package login;

import teacher.TeacherService;
import util.shape.ConfirmMessage;
import util.shape.InputDialog;
import admin.AdminService;
import configuration.uiConfiguration.Message;
import exam.examexecute.ExamExecuteService;

public class LoginService {

	private static LoginService loginService;

	public static LoginService getInstance() {
		if (loginService == null)
			return loginService = new LoginService();
		return loginService;
	}

	public void loginAdmin() {
		String password = InputDialog.create("Enter Admin Password");
		if (password != null && password.equals("admin"))
			AdminService.getInstance().openAdmin();
	}

	public void loginTeacher() {
		String password = InputDialog.create("Enter Teacher Password");
		if (password != null && password.equals("teacher"))
			TeacherService.getInstance().generateFinalSheet();
	}

	public void loginUser() {
		String password = InputDialog.create("Enter Exam Password");
		if (password != null)
			ExamExecuteService.getInstance().executeExam(password);
	}

	public void loginOut() {
		if (ConfirmMessage.confirmMessage(Message.existSystemConfirm)) {
			System.exit(0);
		}
	}
}
