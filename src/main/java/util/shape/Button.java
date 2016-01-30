package util.shape;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.MainService;
import configuration.uiConfiguration.Dimension;

public class Button implements ActionListener {
	public JButton button(Dimension dim, String picPath) {
		JButton button = new JButton("");
		button.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
		Image currentImage = new ImageIcon("Images/buttons/" + picPath + ".png").getImage();
		Image scaledImage = currentImage.getScaledInstance(dim.getW() - 7, dim.getH() - 7, java.awt.Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(scaledImage));
		button.setActionCommand(picPath);
		button.setVisible(true);
		button.addActionListener(this);
		button.setContentAreaFilled(true);
		button.setBorderPainted(true);
		return button;
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		try {
			MainService mainService = MainService.getInstance();
			mainService.getClass().getDeclaredMethod(command).invoke(mainService);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
