package util.shape;

import javax.swing.JOptionPane;

public class Success {

	public static void reportSuccessMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
	}
}
