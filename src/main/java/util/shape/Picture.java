package util.shape;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.Dimension;

public class Picture {
    public static JLabel image(Dimension dim, String picPath) {
        Image pic = null;
        try {
            pic = ImageIO.read(new File(picPath));
        } catch (IOException e) {
            return null;
        }
        Image picScale = pic.getScaledInstance(dim.getW(), dim.getH(), Image.SCALE_SMOOTH);
        JLabel picPanel = new JLabel(new ImageIcon(picScale));
        picPanel.setBounds(dim.getX(), dim.getY(), dim.getW(), dim.getH());
        return picPanel;
    }
}
