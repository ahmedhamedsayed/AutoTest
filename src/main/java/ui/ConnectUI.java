package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Connect;
import models.ConnectPair;
import models.Question;
import constants.ButtonCommand;
import constants.Message;
import constants.dimentions.ConnectDimension;
import constants.paths.ConnectLabelPath;
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

public class ConnectUI implements QuestionUI {

	private static QuestionUI connectUI;
	private JPanel panel;
	private JTextArea[] connectTextArea;
	private JButton[] button;
	private TimerCountDown timerCountDown;
	private Connect connect;
	private final int textAreaCounter = 19, buttonsCounter = 4;

	private void buildPanel() {
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new ConnectDimension(screenWidth, screenHeight);

		JLabel[] label = new JLabel[10];
		for (int i = 0; i < 10; ++i) {
			Dimension curLabelDimension = ConnectDimension.labelDimension[i];
			String curLabelName = ConnectLabelPath.labelName[i];
			label[i] = Label.label(curLabelDimension, curLabelName);
			panel.add(label[i]);
		}

		connectTextArea = new JTextArea[textAreaCounter];
		for (int i = 0; i < textAreaCounter; ++i) {
			Dimension curTextAreaDimension = ConnectDimension.textAreaDimension[i];
			connectTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
			panel.add(connectTextArea[i]);
		}

		JButton[] button = new JButton[buttonsCounter];
		for (int i = 0; i < buttonsCounter; ++i) {
			Dimension curButtonDimension = ConnectDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.connect[i];
			button[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(button[i]);
		}
		timerCountDown = new TimerCountDown(0, 0, 0);
		panel.add(timerCountDown.getLabel());
		panel.add(Picture.image(dim, Message.QUESTION_BACK_GROUND_PATH.getValue()));
	}

	public ConnectUI() {
		buildPanel();
	}

	public static synchronized QuestionUI getInstance() {
		if (connectUI == null)
			return connectUI = new ConnectUI();
		return connectUI;
	}

	public void setTimerCountDown(TimerCountDown timerCountDown) {
		JLabel label = this.timerCountDown.getLabel();
		this.timerCountDown = timerCountDown;
		timerCountDown.setLabel(label);
	}

	public void resetTextArea() {
		for (JTextArea textArea : connectTextArea)
			textArea.setText("");
	}

	public void convertToUI(Question question) {
		connect = (Connect) question;
		for (int i = 0; i < connect.getConnectPairs().size(); ++i) {
			connectTextArea[i].setText(connect.getConnectPairs().get(i).getFirstDescription());
			connectTextArea[i + 6].setText(connect.getConnectPairs().get(i).getSecondDescription());
			connectTextArea[i + 13].setText(String.valueOf(connect.getConnectPairs().get(i).getAnswer()));
		}
		connectTextArea[12].setText(String.valueOf(connect.getMark()));
	}

	private JPanel getPanelForAdminEdit() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.connectTextArea[i].setEditable(true);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i < 2);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
	}

	private JPanel getPanelForAdminShow() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.connectTextArea[i].setEditable(false);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i == 0);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
	}

	private JPanel getPanelForStudent(boolean inEnd) {
		for (int i = 0; i < this.textAreaCounter; ++i) {
			this.connectTextArea[i].setEditable(i >= 13);
			if (i >= 13)
				this.connectTextArea[i].setText("");
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
		Connect newConnect = new Connect();
		if (connect != null)
			newConnect.setId(connect.getId());
		List<ConnectPair> connectPairs = new ArrayList<ConnectPair>();
		for (int i = 0; i < 6; ++i) {
			ConnectPair connectPair = new ConnectPair();
			connectPair.setFirstDescription(connectTextArea[i].getText().trim());
			connectPair.setSecondDescription(connectTextArea[i + 6].getText().trim());
			connectPair.setAnswer(Integer.valueOf(connectTextArea[i + 13].getText().trim()));
			connectPairs.add(connectPair);
		}
		newConnect.setConnectPairs(connectPairs);
		newConnect.setMark(Integer.valueOf(connectTextArea[12].getText().trim()));
		return connect = newConnect;
	}

	public int getMark() {
		int totMark = 0;
		for (int i = 0; i < connect.getConnectPairs().size(); ++i) {
			int studentAnswer;
			try {
				studentAnswer = Integer.parseInt(connectTextArea[i + 13].getText().trim());
			} catch (NumberFormatException e) {
				studentAnswer = -1;
			}
			totMark += (connect.getConnectPairs().get(i).getAnswer() == studentAnswer) ? connect.getMark() : 0;
		}
		return totMark;
	}
}
