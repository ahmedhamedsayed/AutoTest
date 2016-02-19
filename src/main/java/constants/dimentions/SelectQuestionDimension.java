package constants.dimentions;

import util.Dimension;

public class SelectQuestionDimension {
    public static Dimension[] buttonDimension = new Dimension[7];

    public SelectQuestionDimension(int w, int h) {
        setButtonDimension(w / 2, h / 5);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-205 + w, 100 + h, 100, 70);
        buttonDimension[1] = new Dimension(5 + w, 100 + h, 200, 70);
        buttonDimension[2] = new Dimension(-205 + w, 170 + h, 100, 70);
        buttonDimension[3] = new Dimension(55 + w, 170 + h, 100, 70);
        buttonDimension[4] = new Dimension(-205 + w, 240 + h, 100, 70);
        buttonDimension[5] = new Dimension(5 + w, 240 + h, 200, 70);
        buttonDimension[6] = new Dimension(-100 + w, 310 + h, 100, 70);
    }
}
