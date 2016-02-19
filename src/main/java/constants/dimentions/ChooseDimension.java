package constants.dimentions;


import util.Dimension;

public class ChooseDimension {
    public static Dimension[] labelDimension = new Dimension[3];
    public static Dimension[] textAreaDimension = new Dimension[9];
    public static Dimension[] buttonDimension = new Dimension[4];

    public ChooseDimension(int w, int h) {
        setLabelDimension(w / 2, h / 5);
        setTextAreaDimension(w / 2, h / 5);
        setButtonDimension(w / 2, h / 5);
    }

    private void setLabelDimension(int w, int h) {
        labelDimension[0] = new Dimension(183 + w, 0 + h, 60, 25);
        labelDimension[1] = new Dimension(183 + w, 232 + h, 60, 25);
        labelDimension[2] = new Dimension(-182 + w, 232 + h, 150, 25);
    }

    private void setTextAreaDimension(int w, int h) {
        textAreaDimension[0] = new Dimension(-242 + w, 30 + h, 484, 200); // Question.
        textAreaDimension[1] = new Dimension(123 + w, 232 + h, 55, 30);   // Mark.
        textAreaDimension[2] = new Dimension(-242 + w, 232 + h, 55, 30);  // Answer.
        textAreaDimension[3] = new Dimension(-242 + w, 264 + h, 484, 20); // Choices.
        textAreaDimension[4] = new Dimension(-242 + w, 285 + h, 484, 20);
        textAreaDimension[5] = new Dimension(-242 + w, 306 + h, 484, 20);
        textAreaDimension[6] = new Dimension(-242 + w, 327 + h, 484, 20);
        textAreaDimension[7] = new Dimension(-242 + w, 348 + h, 484, 20);
        textAreaDimension[8] = new Dimension(-242 + w, 369 + h, 484, 20);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(172 + w, 390 + h, 70, 25);
        buttonDimension[1] = new Dimension(-242 + w, 390 + h, 70, 25);
        buttonDimension[2] = new Dimension(172 + w, 390 + h, 70, 25);
        buttonDimension[3] = new Dimension(-242 + w, 390 + h, 70, 25);
    }
}
