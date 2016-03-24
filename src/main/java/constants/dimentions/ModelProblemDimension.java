package constants.dimentions;

import util.Dimension;

public class ModelProblemDimension {
	public static Dimension[] labelDimension = new Dimension[3];
	public static Dimension[] textAreaDimension = new Dimension[38];
	public static Dimension[] buttonDimension = new Dimension[4];

	public ModelProblemDimension(int w, int h) {
		setLabelDimension(w / 2, h / 3);
		setTextAreaDimension(w / 2, h / 3);
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
		for (int i = 0; i < 6; ++i)
			for (int j = 0; j < 6; ++j)
				textAreaDimension[6 * i + j + 2] = new Dimension(-300 + 100 * j + w, 140 + 20 * i + h, 100, 20);
	}

	private void setButtonDimension(int w, int h) {
		buttonDimension[0] = new Dimension(200 + w, 310 + h, 100, 30);
		buttonDimension[1] = new Dimension(-300 + w, 310 + h, 100, 30);
		buttonDimension[2] = new Dimension(200 + w, 310 + h, 100, 30);
		buttonDimension[3] = new Dimension(-300 + w, 310 + h, 100, 30);
	}
}
