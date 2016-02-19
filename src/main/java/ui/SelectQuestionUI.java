package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.ButtonCommand;
import constants.dimentions.SelectQuestionDimension;
import constants.paths.BackGroundPicturePath;
import services.SelectQuestionService;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Panel;
import util.shape.Picture;

public class SelectQuestionUI {

	private static SelectQuestionUI selectQuestionUI;
	private JPanel panel;
	private JButton[] buttons;

	public static synchronized SelectQuestionUI getInstance() {
		if (selectQuestionUI == null)
			return selectQuestionUI = new SelectQuestionUI();
		return selectQuestionUI;
	}

	public void resetFocus() {
		buttons[0].requestFocusInWindow();
	}

	public JPanel getQuestionPanel() {
		if (panel != null)
			return panel;
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new SelectQuestionDimension(screenWidth, screenHeight);

		buttons = new JButton[7];
		for (int i = 0; i < 7; ++i) {
			Dimension curButtonDimension = SelectQuestionDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.newQuestion[i];
			buttons[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(buttons[i]);
		}

		panel.add(Picture.image(dim, BackGroundPicturePath.GeneralBackGroundPath));
		buildAction();
		return panel;
	}

	private void buildAction() {
		KeyListener action = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					SelectQuestionService.getInstance().newBack();
				}
			}
		};
		for (JButton button : buttons) {
			button.addKeyListener(action);
		}
	}
}
