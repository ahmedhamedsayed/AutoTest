package util.shape;

import javax.swing.JLabel;

import util.Dimension;
import util.Format;

public class TimerLabel {

	public static JLabel timerLabel(Dimension dim, int fontSize, String text) {
        JLabel label = new JLabel(text);
        label.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
        label.setFont(Format.getFont(fontSize));
        label.applyComponentOrientation(Format.getArabicLanguage());
        label.setVisible(true);
        return label;
    }
}
