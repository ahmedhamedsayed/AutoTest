package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Question;
import models.TrueOrFalse;
import constants.ButtonCommand;
import constants.Message;
import constants.dimentions.TrueOrFalseDimension;
import constants.paths.TrueOrFalseLabelPath;
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

public class TrueOrFalseUI implements QuestionUI {

    private static QuestionUI trueOrFalseUI;
    private JPanel panel;
    private JTextArea[] trueOrFalseTextArea;
    private JButton[] button;
	private TimerCountDown timerCountDown;
	private TrueOrFalse trueOrFalse;
	private final int textAreaCounter = 3, buttonsCounter = 4;

	private void buildPanel() {
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new TrueOrFalseDimension(screenWidth, screenHeight);

        JLabel[] label = new JLabel[3];
        for (int i = 0; i < 3; ++i) {
            Dimension curLabelDimension = TrueOrFalseDimension.labelDimension[i];
            String curLabelName = TrueOrFalseLabelPath.labelName[i];
            label[i] = Label.label(curLabelDimension, curLabelName);
            panel.add(label[i]);
        }

        trueOrFalseTextArea = new JTextArea[textAreaCounter];
        for (int i = 0; i < textAreaCounter; ++i) {
            Dimension curTextAreaDimension = TrueOrFalseDimension.textAreaDimension[i];
            trueOrFalseTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
            panel.add(trueOrFalseTextArea[i]);
        }

        button = new JButton[buttonsCounter];
        for (int i = 0; i < buttonsCounter; ++i) {
            Dimension curButtonDimension = TrueOrFalseDimension.buttonDimension[i];
            String curButtonName = ButtonCommand.trueOrFalse[i];
            button[i] = new Button().button(curButtonDimension, curButtonName);
            panel.add(button[i]);
        }
        timerCountDown = new TimerCountDown(0, 0, 0);
		panel.add(timerCountDown.getLabel());
		panel.add(Picture.image(dim, Message.QUESTION_BACK_GROUND_PATH.getValue()));
	}

	public TrueOrFalseUI() {
		buildPanel();
	}
	
	public static synchronized QuestionUI getInstance() {
        if (trueOrFalseUI == null)
            return trueOrFalseUI = new TrueOrFalseUI();
        return trueOrFalseUI;
    }

	public void setTimerCountDown(TimerCountDown timerCountDown) {
		JLabel label = this.timerCountDown.getLabel();
		this.timerCountDown = timerCountDown;
		timerCountDown.setLabel(label);
	}

	public void resetTextArea() {
		for (JTextArea textArea : trueOrFalseTextArea)
			textArea.setText("");
	}

	public void convertToUI(Question questionModel) {
		trueOrFalse = (TrueOrFalse) questionModel;
		trueOrFalseTextArea[0].setText(trueOrFalse.getDescription());
		trueOrFalseTextArea[1].setText(String.valueOf(trueOrFalse.getMark()));
		trueOrFalseTextArea[2].setText(trueOrFalse.getAnswer());		
	}

	private JPanel getPanelForAdminEdit() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.trueOrFalseTextArea[i].setEditable(true);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i < 2);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
    }
    
    private JPanel getPanelForAdminShow() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.trueOrFalseTextArea[i].setEditable(false);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i == 0);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
    }
    
    private JPanel getPanelForStudent(boolean inEnd) {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.trueOrFalseTextArea[i].setEditable(i == 2);
		this.trueOrFalseTextArea[2].setText("");
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
		TrueOrFalse newTrueOrFalse = new TrueOrFalse();
		if (trueOrFalse != null)
			newTrueOrFalse.setId(trueOrFalse.getId());
		newTrueOrFalse.setDescription(trueOrFalseTextArea[0].getText().trim());
		newTrueOrFalse.setMark(Integer.valueOf(trueOrFalseTextArea[1].getText().trim()));
		newTrueOrFalse.setAnswer(trueOrFalseTextArea[2].getText().trim());
		return trueOrFalse = newTrueOrFalse;
	}

	public int getMark() {
		String studentAnswer = trueOrFalseTextArea[2].getText().trim();
		return (studentAnswer == trueOrFalse.getAnswer()) ? trueOrFalse.getMark() : 0;
	}
}
