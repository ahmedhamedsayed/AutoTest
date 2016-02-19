package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.ButtonCommand;
import constants.dimentions.LoginDimension;
import constants.paths.BackGroundPicturePath;
import services.LoginService;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Panel;
import util.shape.Picture;

public class LoginUI {

	private static LoginUI loginUI;
	private JPanel panel;
	private JButton[] buttons;

	public static synchronized LoginUI getInstance() {
		if (loginUI == null)
			return loginUI = new LoginUI();
		return loginUI;
	}

	public void resetFocus() {
		buttons[0].requestFocusInWindow();
	}

	public JPanel getPanel() {
		if (panel != null)
			return panel;
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new LoginDimension(screenWidth, screenHeight);

		buttons = new JButton[4];
		for (int i = 0; i < 4; ++i) {
			Dimension curButtonDimension = LoginDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.login[i];
			buttons[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(buttons[i]);
		}
		panel.add(Picture.image(dim, BackGroundPicturePath.GeneralBackGroundPath));
		buildAction(buttons);
		return panel;
	}

	private void buildAction(JButton[] buttons) {
		KeyListener action = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_A) {
					LoginService.getInstance().loginAdmin();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_U) {
					LoginService.getInstance().loginUser();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					LoginService.getInstance().loginOut();
				}
			}
		};
		for (JButton button : buttons) {
			button.addKeyListener(action);
		}
	}
}
