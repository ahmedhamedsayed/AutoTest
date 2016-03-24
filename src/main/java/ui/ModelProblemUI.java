package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.ModelProblem;
import models.ModelProblemEntry;
import models.Question;
import constants.ButtonCommand;
import constants.Message;
import constants.dimentions.ModelProblemDimension;
import constants.paths.ModelProblemLabelPath;
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

public class ModelProblemUI implements QuestionUI {

	private static QuestionUI modelProblemUI;
	private JPanel panel;
	private JTextArea[] modelTextArea;
	private JButton[] button;
	private TimerCountDown timerCountDown;
	private ModelProblem modelProblem;
	private final int textAreaCounter = 38, buttonsCounter = 4;

	private void buildPanel() {
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new ModelProblemDimension(screenWidth, screenHeight);

		for (int i = 0; i < 3; ++i) {
			Dimension curLabelDimension = ModelProblemDimension.labelDimension[i];
			String curLabelName = ModelProblemLabelPath.labelName[i];
			JLabel label = Label.label(curLabelDimension, curLabelName);
			panel.add(label);
		}

		modelTextArea = new JTextArea[textAreaCounter];
		for (int i = 0; i < textAreaCounter; ++i) {
			Dimension curTextAreaDimension = ModelProblemDimension.textAreaDimension[i];
			modelTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
			panel.add(modelTextArea[i]);
		}

		button = new JButton[buttonsCounter];
		for (int i = 0; i < buttonsCounter; ++i) {
			Dimension curButtonDimension = ModelProblemDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.model[i];
			button[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(button[i]);
		}
		timerCountDown = new TimerCountDown(0, 0, 0);
		panel.add(timerCountDown.getLabel());
		panel.add(Picture.image(dim, Message.QUESTION_BACK_GROUND_PATH.getValue()));
	}

	public ModelProblemUI() {
		buildPanel();
	}

	public static synchronized QuestionUI getInstance() {
		if (modelProblemUI == null)
			return modelProblemUI = new ModelProblemUI();
		return modelProblemUI;
	}

	public void setTimerCountDown(TimerCountDown timerCountDown) {
		JLabel label = this.timerCountDown.getLabel();
		this.timerCountDown = timerCountDown;
		timerCountDown.setLabel(label);
	}

	public void resetTextArea() {
		for (JTextArea textArea : modelTextArea)
			textArea.setText("");
	}

	public void convertToUI(Question question) {
		modelProblem = (ModelProblem) question;
		modelTextArea[0].setText(modelProblem.getDescription());
		modelTextArea[1].setText(String.valueOf(modelProblem.getMark()));
		for (int i = 0; i < modelProblem.getModelProblemEntries().size(); ++i) {
			ModelProblemEntry modelProblemEntry = modelProblem.getModelProblemEntries().get(i);
			int r = modelProblemEntry.getRow(), c = modelProblemEntry.getCol();
			modelTextArea[6 * r + c + 2].setText(modelProblemEntry.getDescription());
			modelTextArea[6 * r + c + 3].setText(modelProblemEntry.getAnswer());
		}
	}

	private JPanel getPanelForAdminEdit() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.modelTextArea[i].setEditable(true);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i < 2);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
	}

	private JPanel getPanelForAdminShow() {
		for (int i = 0; i < this.textAreaCounter; ++i)
			this.modelTextArea[i].setEditable(false);
		for (int i = 0; i < this.buttonsCounter; ++i)
			this.button[i].setVisible(i == 0);
		this.timerCountDown.getLabel().setVisible(false);
		return panel;
	}

	private JPanel getPanelForStudent(boolean inEnd) {
		for (int i = 0; i < this.textAreaCounter; ++i) {
			this.modelTextArea[i].setEditable(i >= 2 && i % 2 == 1);
			if (i >= 2 && i % 2 == 1)
				this.modelTextArea[i].setText("");
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
		ModelProblem newModelProblem = new ModelProblem();
		if (modelProblem != null)
			newModelProblem.setId(modelProblem.getId());
		newModelProblem.setDescription(modelTextArea[0].getText().trim());
		newModelProblem.setMark(Integer.valueOf(modelTextArea[1].getText().trim()));
		List<ModelProblemEntry> modelProblemEntries = new ArrayList<ModelProblemEntry>();
		for (int i = 0; i < 6; ++i)
			for (int j = 0; j < 6; j += 2) {
				String description = modelTextArea[6 * i + j + 2].getText().trim();
				String answer = modelTextArea[6 * i + j + 3].getText().trim();
				if ("".equals(description) || "".equals(answer))
					continue;
				ModelProblemEntry modelProblemEntry = new ModelProblemEntry();
				modelProblemEntry.setRow(i);
				modelProblemEntry.setCol(j);
				modelProblemEntry.setDescription(description);
				modelProblemEntry.setAnswer(answer);
				modelProblemEntries.add(modelProblemEntry);
			}
		newModelProblem.setModelProblemEntries(modelProblemEntries);
		return modelProblem = newModelProblem;
	}

	public int getMark() {
		int totMark = 0;
		for (int i = 0; i < modelProblem.getModelProblemEntries().size(); ++i) {
			ModelProblemEntry modelProblemEntry = modelProblem.getModelProblemEntries().get(i);
			int r = modelProblemEntry.getRow(), c = modelProblemEntry.getCol();
			String studentAnswer = modelTextArea[6 * r + c + 3].getText().trim();
			totMark += modelProblemEntry.getAnswer().equals(studentAnswer) ? modelProblem.getMark() : 0;
		}
		return totMark;
	}
}
