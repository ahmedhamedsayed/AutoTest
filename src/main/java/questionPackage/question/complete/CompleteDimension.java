package questionPackage.question.complete;

import configuration.uiConfiguration.Dimension;

public class CompleteDimension {
    public static Dimension[] labelDimension = new Dimension[3];
    public static Dimension[] textAreaDimension = new Dimension[3];
    public static Dimension[] buttonDimension = new Dimension[2];

    public CompleteDimension(int w, int h) {
        setLabelDimension(w / 2, h / 5);
        setTextAreaDimension(w / 2, h / 5);
        setButtonDimension(w / 2, h / 5);
    }

    private void setLabelDimension(int w, int h) {
        labelDimension[0] = new Dimension(-192 + w, 0 + h, 430, 30);
        labelDimension[1] = new Dimension(187 + w, 232 + h, 55, 30);
        labelDimension[2] = new Dimension(187 + w, 364 + h, 55, 30);
    }

    private void setTextAreaDimension(int w, int h) {
        textAreaDimension[0] = new Dimension(-242 + w, 31 + h, 484, 200);
        textAreaDimension[1] = new Dimension(131 + w, 364 + h, 55, 30);
        textAreaDimension[2] = new Dimension(-242 + w, 263 + h, 484, 100);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-242 + w, 400 + h, 100, 30);
        buttonDimension[1] = new Dimension(141 + w, 400 + h, 100, 30);
    }
}
