package util.shape;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import util.Dimension;
import util.Format;

public class TextArea {

	public static JTextArea textArea(Dimension dim, int fontSize) {
        JTextArea textArea = new JTextArea("");
        textArea.setLineWrap(true);
        textArea.setFont(Format.getFont(fontSize));
        textArea.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea.applyComponentOrientation(Format.getArabicLanguage());
        textArea.setVisible(true);
        return textArea;
    }
}
