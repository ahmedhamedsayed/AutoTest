package util.shape;

import javax.swing.JOptionPane;

import constants.Message;

public class InputDialog {
	
	public static String create(String massage) {
		return JOptionPane.showInputDialog(massage);
	}
	
	public static String createToWaitValue(String massage, String errorMessage) {
		while (true) {
			String input = JOptionPane.showInputDialog(massage);
			if (input == null)
				return null;
			if (!input.trim().isEmpty())
				return input;
			Error.reportErrorMessage(errorMessage);
		}
	}
	
	public static boolean createToWaitPassword(String massage, String password) {
		while (true) {
			String inputPassword = JOptionPane.showInputDialog(massage);
			if (password == null)
				return false;
			if (password.equals(inputPassword))
				return true;
			Error.reportErrorMessage(Message.PASSWORD_WRONG.getValue());
		}
	}
}
