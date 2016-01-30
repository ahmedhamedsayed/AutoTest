package util.shape;

import javax.swing.JPanel;

import configuration.uiConfiguration.Dimension;

public class Panel {

    public static JPanel create(Dimension dim) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
        panel.setVisible(true);
        return panel;
    }
}
