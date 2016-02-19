package util.shape;

import javax.swing.JList;
import javax.swing.JScrollPane;

import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;

public class ScrollPane {
    
	public static JScrollPane scrollPane(Dimension dim, int fontSize, JList<String> list) {
        list.setFont(Format.getFont(fontSize));
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
        scrollPane.applyComponentOrientation(Format.getArabicLanguage());
        scrollPane.setVisible(true);
        return scrollPane;
    }
}
