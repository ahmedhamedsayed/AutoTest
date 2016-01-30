package questionPackage.question.choose;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import questionPackage.question.Question;
import questionPackage.question.QuestionService;
import questionPackage.question.QuestionUI;
import questionPackage.question.choose.choice.Choice;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.TextArea;
import util.shape.TimerCountDown;
import configuration.commandConfiguration.ButtonCommand;
import configuration.uiConfiguration.BackGroundPicturePath;
import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;
import exam.examexecute.ExamExecuteService;

public class ChooseUI implements QuestionUI {

	private static QuestionUI chooseUI;
	private JPanel panel;
	private JTextArea[] chooseTextArea;
	private JButton[] button;
	private TimerCountDown timerCountDown;
	private Choose choose;
	private int currentTextArea, correctChoise;
	private final int textAreaCounter = 9, buttonsCounter = 4;

	private void buildPanel() {
		currentTextArea = 0;
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new ChooseDimension(screenWidth, screenHeight);

		JLabel[] label = new JLabel[3];
		for (int i = 0; i < 3; ++i) {
			Dimension curLabelDimension = ChooseDimension.labelDimension[i];
			String curLabelName = ChooseLabelPath.labelName[i];
			label[i] = Label.label(curLabelDimension, 20, curLabelName);
			panel.add(label[i]);
		}

		chooseTextArea = new JTextArea[textAreaCounter];
		for (int i = 0; i < textAreaCounter; ++i) {
			Dimension curTextAreaDimension = ChooseDimension.textAreaDimension[i];
			chooseTextArea[i] = TextArea.textArea(curTextAreaDimension, 20);
			panel.add(chooseTextArea[i]);
		}

		button = new JButton[buttonsCounter];
		for (int i = 0; i < buttonsCounter; ++i) {
			Dimension curButtonDimension = ChooseDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.choose[i];
			button[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(button[i]);
		}
		timerCountDown = new TimerCountDown(0, 0, 0);
		panel.add(timerCountDown.getLabel());
		panel.add(Picture.image(dim, BackGroundPicturePath.QuestionBackGroundPath));
	}

	private void buildAction() {
		KeyListener saveAction = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					++currentTextArea;
					if (currentTextArea == textAreaCounter)
						currentTextArea = 0;
					moveFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					--currentTextArea;
					if (currentTextArea == -1)
						currentTextArea = textAreaCounter - 1;
					moveFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ChooseService.getInstance().chooseBack();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S) {
					ChooseService.getInstance().chooseDone();
					moveFocus();
				}
			}
		};

		for (int i = 0; i < textAreaCounter; ++i) {
			chooseTextArea[i].addKeyListener(saveAction);
		}
	}

	public ChooseUI() {
		buildPanel();
		//buildAction();
	}

	public static synchronized QuestionUI getInstance() {
		if (chooseUI == null) {
			return chooseUI = new ChooseUI();
		}
		return chooseUI;
	}

	public void setTimerCountDown(TimerCountDown timerCountDown) {
		JLabel label = this.timerCountDown.getLabel();
		this.timerCountDown = timerCountDown;
		timerCountDown.setLabel(label);
	}

	public void resetTextArea() {
		for (JTextArea textArea : chooseTextArea)
			textArea.setText("");
	}

	public void convertToUI(Question questionModel) {
		choose = (Choose) questionModel;
		chooseTextArea[0].setText(choose.getDescription());
		chooseTextArea[1].setText(String.valueOf(choose.getMark()));
		chooseTextArea[2].setText(String.valueOf(choose.getAnswer()));

		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < choose.getChoices().size(); ++i)
			indexes.add(i);

		Random random = new Random();
		for (int i = 0; i < choose.getChoices().size(); ++i) {
			int index = random.nextInt(indexes.size());
			if (indexes.get(index) + 1 == choose.getAnswer())
				correctChoise = i + 1;
			chooseTextArea[i + 3].setText(choose.getChoices().get(indexes.get(index)).getDescription());
			indexes.remove(index);
		}
	}

	private JPanel getPanelForAdminEdit() {
    	this.currentTextArea = 0;
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.chooseTextArea[i].setEditable(true);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i < 2);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
    }
    
    private JPanel getPanelForAdminShow() {
    	this.currentTextArea = -1;
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.chooseTextArea[i].setEditable(false);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i == 0);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
    }
    
    private JPanel getPanelForStudent(boolean inEnd) {
    	this.currentTextArea = 2;
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.chooseTextArea[i].setEditable(i == 2);
		this.chooseTextArea[2].setText("");
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

	public void moveFocus() {
		//chooseTextArea[currentTextArea].requestFocusInWindow();
		//chooseTextArea[currentTextArea].setCaretPosition(chooseTextArea[currentTextArea].getText().length());
		//chooseTextArea[currentTextArea].getCaret().setVisible(true);
	}

	public Question convertToModel() {
		Choose newChoose = new Choose();
		if (choose != null)
			newChoose.setId(choose.getId());
		newChoose.setDescription(chooseTextArea[0].getText().trim());
		newChoose.setMark(Integer.valueOf(chooseTextArea[1].getText().trim()));
		newChoose.setAnswer(Integer.valueOf(chooseTextArea[2].getText().trim()));
		List<Choice> choices = new ArrayList<Choice>();
		for (int i = 3; i < chooseTextArea.length; ++i) {
			if (!chooseTextArea[i].getText().trim().equals("")) {
				Choice choice = new Choice();
				choice.setId(0);
				choice.setDescription(chooseTextArea[i].getText().trim());
				choices.add(choice);
			}
		}
		newChoose.setChoices(choices);
		return choose = newChoose;
	}
	
	public int getMark() {
		int studentChoise;
		try {
			studentChoise = Integer.parseInt(chooseTextArea[2].getText());
		} catch (NumberFormatException e) {
			studentChoise = -1;
		}
		return (studentChoise == correctChoise) ? choose.getMark() : 0;
	}
}
