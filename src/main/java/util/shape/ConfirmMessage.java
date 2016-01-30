package util.shape;

import javax.swing.JOptionPane;

public class ConfirmMessage {

	public static boolean confirmMessage(String message) {
		return (JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
}
