package questionPackage.question.modelproblem;

import configuration.uiConfiguration.Dimension;

public class ModelProblemDimension {
    public static Dimension[] labelDimension = new Dimension[3];
    public static Dimension[] textAreaDimension = new Dimension[2];
    public static Dimension[] tableDimension = new Dimension[1];
    public static Dimension[] buttonDimension = new Dimension[2];

    public ModelProblemDimension(int w, int h) {
        setLabelDimension(w / 2, h / 3);
        setTextAreaDimension(w / 2, h / 3);
        setTableDimension(w / 2, h / 3);
        setButtonDimension(w / 2, h / 3);
    }

    private void setLabelDimension(int w, int h) {
        labelDimension[0] = new Dimension(150 + w, -55 + h, 150, 30);
        labelDimension[1] = new Dimension(150 + w, 110 + h, 150, 30);
        labelDimension[2] = new Dimension(150 + w, 280 + h, 150, 30);
    }

    private void setTextAreaDimension(int w, int h) {
        textAreaDimension[0] = new Dimension(-300 + w, -20 + h, 600, 120);
        textAreaDimension[1] = new Dimension(100 + w, 280 + h, 55, 30);
    }

    private void setTableDimension(int w, int h) {
        tableDimension[0] = new Dimension(-300 + w, 140 + h, 600, 120);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-300 + w, 310 + h, 100, 30);
        buttonDimension[1] = new Dimension(200 + w, 310 + h, 100, 30);
    }
}
