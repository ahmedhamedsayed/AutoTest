package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Law;
import models.LawAnswer;
import models.MathProblem;
import models.Question;
import constants.ButtonCommand;
import constants.Message;
import constants.dimentions.MathProblemDimension;
import constants.paths.MathProblemLabelPath;
import services.ExamExecuteService;
import services.QuestionService;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.TextArea;
import util.shape.TimerCountDown;

public class MathProblemUI implements QuestionUI {

	private static QuestionUI mathProblemUI;
	private JPanel panel;
	private JTextArea[] mathProblemTextArea;
	private JButton[] button;
	private TimerCountDown timerCountDown;
	private MathProblem mathProblem;
	private final int textAreaCounter = 16, buttonsCounter = 4;

	private void buildPanel() {
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new MathProblemDimension(screenWidth, screenHeight);

		JLabel[] label = new JLabel[6];
		for (int i = 0; i < 6; ++i) {
			Dimension curLabelDimension = MathProblemDimension.labelDimension[i];
			String curLabelName = MathProblemLabelPath.labelName[i];
			label[i] = Label.label(curLabelDimension, curLabelName);
			panel.add(label[i]);
		}

		mathProblemTextArea = new JTextArea[textAreaCounter];
		for (int i = 0; i < textAreaCounter; ++i) {
			Dimension curTextAreaDimension = MathProblemDimension.textAreaDimension[i];
			mathProblemTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
			panel.add(mathProblemTextArea[i]);
		}

		button = new JButton[buttonsCounter];
		for (int i = 0; i < buttonsCounter; ++i) {
			Dimension curButtonDimension = MathProblemDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.mathProblem[i];
			button[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(button[i]);
		}
		timerCountDown = new TimerCountDown(0, 0, 0);
		panel.add(timerCountDown.getLabel());
		panel.add(Picture.image(dim, Message.QUESTION_BACK_GROUND_PATH.getValue()));
	}

	public MathProblemUI() {
		buildPanel();
	}

	public static synchronized QuestionUI getInstance() {
		if (mathProblemUI == null)
			return mathProblemUI = new MathProblemUI();
		return mathProblemUI;
	}

	public void setTimerCountDown(TimerCountDown timerCountDown) {
		JLabel label = this.timerCountDown.getLabel();
		this.timerCountDown = timerCountDown;
		timerCountDown.setLabel(label);
	}

	public void resetTextArea() {
		for (JTextArea textArea : mathProblemTextArea)
			textArea.setText("");
	}

	public void convertToUI(Question question) {
		mathProblem = (MathProblem) question;
		mathProblemTextArea[0].setText(mathProblem.getDescription());
		mathProblemTextArea[1].setText(String.valueOf(mathProblem.getLawMark()));
		mathProblemTextArea[2].setText(String.valueOf(mathProblem.getAnswer()));
		for (int i = 0; i < mathProblem.getLaws().size(); ++i)
			mathProblemTextArea[i + 3].setText(mathProblem.getLaws().get(i).getDescription());
		for (int i = 0; i < mathProblem.getLawAnswers().size(); ++i)
			mathProblemTextArea[14 - i].setText(String.valueOf(mathProblem.getLawAnswers().get(i).getDescription()));
		mathProblemTextArea[15].setText(String.valueOf(mathProblem.getNumberMark()));
	}

	private JPanel getPanelForAdminEdit() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.mathProblemTextArea[i].setEditable(true);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i < 2);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
	}

	private JPanel getPanelForAdminShow() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.mathProblemTextArea[i].setEditable(false);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i == 0);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
	}

	private JPanel getPanelForStudent(boolean inEnd) {
		for (int i = 0; i < this.textAreaCounter; ++i) {
			this.mathProblemTextArea[i].setEditable(i == 2 || (i >= 9 && i <= 14));
			if (i == 2 || (i >= 9 && i <= 14))
				this.mathProblemTextArea[i].setText("");
		}
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible((i == 2 && !inEnd) || i == 3);
		this.timerCountDown.getLabel().setVisible(true);
		return panel;
	}

	public JPanel getPanel() {
		if (QuestionService.getInstance().isAdminEdit())
			return getPanelForAdminEdit();
		if (QuestionService.getInstance().isAdminShow())
			return getPanelForAdminShow();
		if (QuestionService.getInstance().isStudent())
			return getPanelForStudent(ExamExecuteService.getInstance().isInEnd());
		return null;
	}

	public Question convertToModel() {
		MathProblem newMathProblem = new MathProblem();
		if (mathProblem != null)
			newMathProblem.setId(mathProblem.getId());
		newMathProblem.setDescription(mathProblemTextArea[0].getText().trim());
		newMathProblem.setLawMark(Integer.valueOf(mathProblemTextArea[1].getText().trim()));
		newMathProblem.setAnswer(Integer.valueOf(mathProblemTextArea[2].getText().trim()));
		List<Law> laws = new ArrayList<Law>();
		for (int i = 0; i < 6; ++i) {
			String description = mathProblemTextArea[i + 3].getText().trim();
			if (!"".equals(description)) {
				Law law = new Law();
				law.setDescription(description);
				laws.add(law);
			}
		}
		newMathProblem.setLaws(laws);
		List<LawAnswer> lawAnswers = new ArrayList<LawAnswer>();
		for (int i = 0; i < 6; ++i) {
			String description = mathProblemTextArea[14 - i].getText().trim();
			if (!"".equals(description)) {
				LawAnswer lawAnswer = new LawAnswer();
				lawAnswer.setDescription(Integer.valueOf(description));
				lawAnswers.add(lawAnswer);
			}
		}
		newMathProblem.setLawAnswers(lawAnswers);
		newMathProblem.setNumberMark(Integer.valueOf(mathProblemTextArea[15].getText().trim()));
		return mathProblem = newMathProblem;
	}

	public int getMark() {
		int totMark = 0, studentAnswer;
		try {
			studentAnswer = Integer.parseInt(mathProblemTextArea[2].getText().trim());
		} catch (NumberFormatException e) {
			studentAnswer = -1;
		}
		if (studentAnswer != mathProblem.getAnswer())
			return totMark;
		totMark += mathProblem.getLawMark();
		for (int i = 0; i < mathProblem.getLawAnswers().size(); ++i) {
			try {
				studentAnswer = Integer.parseInt(mathProblemTextArea[14 - i].getText().trim());
			} catch (NumberFormatException e) {
				studentAnswer = -1;
			}
			totMark += (mathProblem.getLawAnswers().get(i).getDescription() == studentAnswer) ? mathProblem.getNumberMark() : 0;
		}
		return totMark;
	}
}
