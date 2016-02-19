package constants.dimentions;

import util.Dimension;

public class ConnectDimension {
    public static Dimension[] labelDimension = new Dimension[10];
    public static Dimension[] textAreaDimension = new Dimension[19];
    public static Dimension[] buttonDimension = new Dimension[2];

    public ConnectDimension(int w, int h) {
        setLabelDimension(w / 2, h / 7);
        setTextAreaDimension(w / 2, h / 7);
        setButtonDimension(w / 2, h / 7);
    }

    private void setLabelDimension(int w, int h) {
        labelDimension[0] = new Dimension(142 + w, 0 + h, 100, 30);
        labelDimension[1] = new Dimension(-172 + w, 277 + h, 80, 30);
        labelDimension[2] = new Dimension(142 + w, 277 + h, 100, 30);
        labelDimension[3] = new Dimension(-353 + w, 277 + h, 100, 30);

        labelDimension[4] = new Dimension(162 + w, 31 + h, 100, 30);
        labelDimension[5] = new Dimension(162 + w, 72 + h, 100, 30);
        labelDimension[6] = new Dimension(162 + w, 113 + h, 100, 30);
        labelDimension[7] = new Dimension(162 + w, 154 + h, 100, 30);
        labelDimension[8] = new Dimension(162 + w, 195 + h, 100, 30);
        labelDimension[9] = new Dimension(162 + w, 236 + h, 100, 30);
    }

    private void setTextAreaDimension(int w, int h) {
        textAreaDimension[0] = new Dimension(-242 + w, 31 + h, 484, 40);
        textAreaDimension[1] = new Dimension(-242 + w, 72 + h, 484, 40);
        textAreaDimension[2] = new Dimension(-242 + w, 113 + h, 484, 40);
        textAreaDimension[3] = new Dimension(-242 + w, 154 + h, 484, 40);
        textAreaDimension[4] = new Dimension(-242 + w, 195 + h, 484, 40);
        textAreaDimension[5] = new Dimension(-242 + w, 236 + h, 484, 40);
        textAreaDimension[6] = new Dimension(-242 + w, 308 + h, 484, 40);
        textAreaDimension[7] = new Dimension(-242 + w, 349 + h, 484, 40);
        textAreaDimension[8] = new Dimension(-242 + w, 390 + h, 484, 40);
        textAreaDimension[9] = new Dimension(-242 + w, 431 + h, 484, 40);
        textAreaDimension[10] = new Dimension(-242 + w, 472 + h, 484, 40);
        textAreaDimension[11] = new Dimension(-242 + w, 513 + h, 484, 40);
        textAreaDimension[12] = new Dimension(-242 + w, 277 + h, 55, 30);
        textAreaDimension[13] = new Dimension(-302 + w, 308 + h, 50, 40);
        textAreaDimension[14] = new Dimension(-302 + w, 349 + h, 50, 40);
        textAreaDimension[15] = new Dimension(-302 + w, 390 + h, 50, 40);
        textAreaDimension[16] = new Dimension(-302 + w, 431 + h, 50, 40);
        textAreaDimension[17] = new Dimension(-302 + w, 472 + h, 50, 40);
        textAreaDimension[18] = new Dimension(-302 + w, 513 + h, 50, 40);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-242 + w, 555 + h, 100, 30);
        buttonDimension[1] = new Dimension(141 + w, 555 + h, 100, 30);
    }
}
