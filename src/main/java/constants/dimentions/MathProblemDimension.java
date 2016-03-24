package constants.dimentions;

import util.Dimension;

public class MathProblemDimension {
	public static Dimension[] labelDimension = new Dimension[6];
	public static Dimension[] textAreaDimension = new Dimension[16];
	public static Dimension[] buttonDimension = new Dimension[4];

	public MathProblemDimension(int w, int h) {
		setLabelDimension(w / 2, h / 5);
		setTextAreaDimension(w / 2, h / 5);
		setButtonDimension(w / 2, h / 5);
	}

	private void setLabelDimension(int w, int h) {
		labelDimension[0] = new Dimension(187 + w, 0 + h, 55, 30);
		labelDimension[1] = new Dimension(21 + w, 182 + h, 55, 30);
		labelDimension[2] = new Dimension(-200 + w, 182 + h, 150, 30);
		labelDimension[3] = new Dimension(182 + w, 182 + h, 60, 30);
		labelDimension[4] = new Dimension(32 + w, 339 + h, 210, 30);
		labelDimension[5] = new Dimension(32 + w, 389 + h, 210, 20);
	}

	private void setTextAreaDimension(int w, int h) {
		textAreaDimension[0] = new Dimension(-242 + w, 31 + h, 484, 150);
		textAreaDimension[1] = new Dimension(-25 + w, 182 + h, 55, 30);
		textAreaDimension[2] = new Dimension(-242 + w, 182 + h, 55, 30);
		
		textAreaDimension[3] = new Dimension(-242 + w, 213 + h, 484, 20);
		textAreaDimension[4] = new Dimension(-242 + w, 234 + h, 484, 20);
		textAreaDimension[5] = new Dimension(-242 + w, 255 + h, 484, 20);
		textAreaDimension[6] = new Dimension(-242 + w, 276 + h, 484, 20);
		textAreaDimension[7] = new Dimension(-242 + w, 297 + h, 484, 20);
		textAreaDimension[8] = new Dimension(-242 + w, 318 + h, 484, 20);
		
		textAreaDimension[9] = new Dimension(-242 + w, 364 + h, 80, 20);
		textAreaDimension[10] = new Dimension(-161 + w, 364 + h, 80, 20);
		textAreaDimension[11] = new Dimension(-80 + w, 364 + h, 80, 20);
		textAreaDimension[12] = new Dimension(1 + w, 364 + h, 80, 20);
		textAreaDimension[13] = new Dimension(82 + w, 364 + h, 80, 20);
		textAreaDimension[14] = new Dimension(163 + w, 364 + h, 80, 20);
		
		textAreaDimension[15] = new Dimension(47 + w, 385 + h, 55, 30);
	}

	private void setButtonDimension(int w, int h) {
		buttonDimension[0] = new Dimension(141 + w, 420 + h, 100, 30);
		buttonDimension[1] = new Dimension(-242 + w, 420 + h, 100, 30);
		buttonDimension[2] = new Dimension(141 + w, 420 + h, 100, 30);
		buttonDimension[3] = new Dimension(-242 + w, 420 + h, 100, 30);
	}
}
