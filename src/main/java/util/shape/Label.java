package util.shape;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import configuration.uiConfiguration.Dimension;

public class Label {
    public static JLabel label(Dimension dim, String picPath) {
        JLabel label = new JLabel("");
        label.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());

		Image currentImage = new ImageIcon("Images/labels/" + picPath + ".png").getImage();
		Image scaledImage = currentImage.getScaledInstance(dim.getW(), dim.getH(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(scaledImage));
        label.setVisible(true);
        return label;
    }
}
