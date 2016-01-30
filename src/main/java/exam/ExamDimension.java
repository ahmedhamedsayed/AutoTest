package exam;

import configuration.uiConfiguration.Dimension;

public class ExamDimension {
    public static Dimension[] labelDimension = new Dimension[5];
    public static Dimension[] textAreaDimension = new Dimension[6];
    public static Dimension[] buttonDimension = new Dimension[5];
    public static Dimension[] scrollPaneDimension = new Dimension[2];

    public ExamDimension(int w, int h) {
        setLabelDimension(w, h);
        setTextAreaDimension(w, h);
        setButtonDimension(w, h);
        setScrollPaneDimension(w, h);
    }

    private void setLabelDimension(int w, int h) {
        labelDimension[0] = new Dimension(-220 + w, 100, 50, 30);
        labelDimension[1] = new Dimension(-410 + w, 100, 50, 30);
        labelDimension[2] = new Dimension(-545 + w, 100, 50, 30);
        labelDimension[3] = new Dimension(-785 + w, 100, 100, 30);
        labelDimension[4] = new Dimension(-1025 + w, 100, 100, 30);
    }

    private void setTextAreaDimension(int w, int h) {
        textAreaDimension[0] = new Dimension(-355 + w, 100, 130, 30);
        textAreaDimension[1] = new Dimension(-453 + w, 100, 38, 30);
        textAreaDimension[2] = new Dimension(-490 + w, 100, 38, 30);
        textAreaDimension[3] = new Dimension(-680 + w, 100, 130, 30);
        textAreaDimension[4] = new Dimension(-920 + w, 100, 130, 30);
        textAreaDimension[5] = new Dimension(-1160 + w, 100, 130, 30);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-150 + w, 150, 130, 25);
        buttonDimension[1] = new Dimension(-150 + w, 185, 130, 25);
        buttonDimension[2] = new Dimension(-120 + w, 220, 70, 25);
        buttonDimension[3] = new Dimension(-120 + w, 255, 70, 25);
        buttonDimension[4] = new Dimension(-780 + w, 150, 130, 25);
    }

    private void setScrollPaneDimension(int w, int h) {
        scrollPaneDimension[0] = new Dimension(-620 + w, 150, 450, 500);
        scrollPaneDimension[1] = new Dimension(-1200 + w, 150, 400, 500);
    }
}
