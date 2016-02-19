package constants.dimentions;

import util.Dimension;
import util.Format;

public class AdminDimension {

	private static AdminDimension adminDimension;
	public Dimension[] buttonDimension = new Dimension[10];
	public Dimension[] scrollPaneDimension = new Dimension[2];

	public static synchronized AdminDimension getInstance() {
		if (adminDimension == null)
			return adminDimension = new AdminDimension();
		return adminDimension;
	}

	public AdminDimension() {
		int screenWidth = Format.screenWidth(), screenHeight = Format.screenHeight();
		setButtonDimension(screenWidth, screenHeight);
		setScrollPaneDimension(screenWidth, screenHeight);
	}

	private void setButtonDimension(int w, int h) {
		buttonDimension[0] = new Dimension(-240 + w, 610, 70, 25);
		buttonDimension[1] = new Dimension(-150 + w, 100, 70, 25);
		buttonDimension[2] = new Dimension(-150 + w, 135, 70, 25);
		buttonDimension[3] = new Dimension(-150 + w, 170, 70, 25);
		buttonDimension[4] = new Dimension(-620 + w, 610, 70, 25);
		buttonDimension[5] = new Dimension(-150 + w, 205, 70, 25);
		buttonDimension[6] = new Dimension(-770 + w, 100, 120, 25);
		buttonDimension[7] = new Dimension(-745 + w, 135, 70, 25);
		buttonDimension[8] = new Dimension(-745 + w, 170, 70, 25);
		buttonDimension[9] = new Dimension(-745 + w, 205, 70, 25);
	}

	private void setScrollPaneDimension(int w, int h) {
		scrollPaneDimension[0] = new Dimension(-620 + w, 100, 450, 500);
		scrollPaneDimension[1] = new Dimension(-990 + w, 100, 200, 500);
	}
}
