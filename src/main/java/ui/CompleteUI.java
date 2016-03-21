package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Complete;
import models.Question;
import constants.ButtonCommand;
import constants.Message;
import constants.dimentions.CompleteDimension;
import constants.paths.CompleteLabelPath;
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

public class CompleteUI implements QuestionUI {

	private static QuestionUI completeUI;
	private JPanel panel;
	private JTextArea[] completeTextArea;
	private JButton[] button;
	private TimerCountDown timerCountDown;
	private Complete complete;
	private final int textAreaCounter = 3, buttonsCounter = 4;

	private void buildPanel() {
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new CompleteDimension(screenWidth, screenHeight);

		JLabel[] label = new JLabel[3];
		for (int i = 0; i < 3; ++i) {
			Dimension curLabelDimension = CompleteDimension.labelDimension[i];
			String curLabelName = CompleteLabelPath.labelName[i];
			label[i] = Label.label(curLabelDimension, curLabelName);
			panel.add(label[i]);
		}

		completeTextArea = new JTextArea[textAreaCounter];
		for (int i = 0; i < textAreaCounter; ++i) {
			Dimension curTextAreaDimension = CompleteDimension.textAreaDimension[i];
			completeTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
			panel.add(completeTextArea[i]);
		}

		button = new JButton[buttonsCounter];
		for (int i = 0; i < buttonsCounter; ++i) {
			Dimension curButtonDimension = CompleteDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.complete[i];
			button[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(button[i]);
		}
		timerCountDown = new TimerCountDown(0, 0, 0);
		panel.add(timerCountDown.getLabel());
		panel.add(Picture.image(dim, Message.QUESTION_BACK_GROUND_PATH.getValue()));
	}

	public CompleteUI() {
		buildPanel();
	}

	public static synchronized QuestionUI getInstance() {
		if (completeUI == null)
			return completeUI = new CompleteUI();
		return completeUI;
	}

	public void setTimerCountDown(TimerCountDown timerCountDown) {
		JLabel label = this.timerCountDown.getLabel();
		this.timerCountDown = timerCountDown;
		timerCountDown.setLabel(label);
	}
	
	public void resetTextArea() {
		for (JTextArea textArea : completeTextArea)
			textArea.setText("");
	}

	public void convertToUI(Question questionModel) {
		complete = (Complete) questionModel;
		completeTextArea[0].setText(complete.getDescription());
		completeTextArea[1].setText(String.valueOf(complete.getMark()));
		completeTextArea[2].setText(complete.getAnswer());		
	}

	private JPanel getPanelForAdminEdit() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.completeTextArea[i].setEditable(true);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i < 2);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
    }

    private JPanel getPanelForAdminShow() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.completeTextArea[i].setEditable(false);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i == 0);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
    }

    private JPanel getPanelForStudent(boolean inEnd) {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.completeTextArea[i].setEditable(i == 2);
		this.completeTextArea[2].setText("");
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
		Complete newComplete = new Complete();
		if (complete != null)
			newComplete.setId(complete.getId());
		newComplete.setDescription(completeTextArea[0].getText().trim());
		newComplete.setMark(Integer.valueOf(completeTextArea[1].getText().trim()));
		newComplete.setAnswer(completeTextArea[2].getText().trim());
		return complete = newComplete;
	}

	public int getMark() {
		String studentAnswer = completeTextArea[2].getText().trim();
		return (studentAnswer.equals(complete.getAnswer())) ? complete.getMark() : 0;
	}
}
