package configuration.uiConfiguration;

public class Dimension {
    private int X, Y, W, H;

    public Dimension(int x, int y, int w, int h) {
        X = x;
        Y = y;
        W = w;
        H = h;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }
}
