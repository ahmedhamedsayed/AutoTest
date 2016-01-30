package util.shape;

import javax.swing.JFileChooser;

public class Chooser {

	public static String chooser(String chooserTitle) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(chooserTitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.showSaveDialog(null);
		return chooser.getSelectedFile().getAbsolutePath();
	}

}
