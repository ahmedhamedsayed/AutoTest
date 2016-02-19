package util.shape;

import javax.swing.JOptionPane;

public class Error {

	public static void reportErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void reportErrorMessageWithException(Exception e, String message) {
		e.printStackTrace();
		reportErrorMessage(message);
	}
}
