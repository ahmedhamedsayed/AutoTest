package util.shape;

import javax.swing.JOptionPane;

public class InputDialog {

	public static String create(String massage) {
		String inputValue = "";
		do {
			inputValue = JOptionPane.showInputDialog(massage);
		} while (inputValue != null && inputValue.trim().equals(""));
		return inputValue;
	}
}
