package login;

import configuration.uiConfiguration.Dimension;

public class LoginDimension {
    public static Dimension[] buttonDimension = new Dimension[4];

    public LoginDimension(int w, int h) {
        setButtonDimension(w / 2, h / 2);
    }

    private void setButtonDimension(int w, int h) {
        buttonDimension[0] = new Dimension(-100 + w, -140 + h, 200, 60);
        buttonDimension[1] = new Dimension(-100 + w, -60 + h, 200, 60);
        buttonDimension[2] = new Dimension(-100 + w, 20 + h, 200, 60);
        buttonDimension[3] = new Dimension(-50 + w, 100 + h, 100, 60);
    }
}
