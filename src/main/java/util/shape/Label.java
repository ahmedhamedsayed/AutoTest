package util.shape;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;

public class Label {
    public static JLabel label(Dimension dim, int fontSize, String picPath) {
        JLabel label = new JLabel("");
        label.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());

		Image currentImage = new ImageIcon("Images/labels/" + picPath + ".png").getImage();
		Image scaledImage = currentImage.getScaledInstance(dim.getW(), dim.getH(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(scaledImage));
        label.setFont(Format.getFont(fontSize));
        label.applyComponentOrientation(Format.getArabicLanguage());
        label.setVisible(true);
        return label;
    }
}
