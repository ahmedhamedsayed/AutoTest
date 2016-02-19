package constants.dimentions;


import util.Dimension;

public class TrueOrFalseDimension {
    public static Dimension[] labelDimension = new Dimension[3];
    public static Dimension[] textAreaDimension = new Dimension[3];
    public static Dimension[] buttonDimension = new Dimension[2];

    public TrueOrFalseDimension(int w, int h) {
        setLabelDimension(w / 2, h / 5);
        setTextAreaDimension(w / 2, h / 5);
        setButtonDimension(w / 2, h / 5);
    }

    private void setLabelDimension(int w, int h) {
        labelDimension[0] = new Dimension(173 + w, 0 + h, 70, 30);
        labelDimension[1] = new Dimension(188 + w, 230 + h, 55, 30);
        labelDimension[2] = new Dimension(-212 + w, 230 + h, 150, 30);
    }

    private void setTextAreaDimension(int w, int h) {
        textAreaDimension[0] = new Dimension(-242 + w, 30 + h, 484, 200);
        textAreaDimension[1] = new Dimension(138 + w, 232 + h, 55, 30);
        textAreaDimension[2] = new Dimension(-242 + w, 232 + h, 55, 30);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-242 + w, 270 + h, 100, 30);
        buttonDimension[1] = new Dimension(141 + w, 270 + h, 100, 30);
    }
}
