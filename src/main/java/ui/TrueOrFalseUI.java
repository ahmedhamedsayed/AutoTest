package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Question;
import constants.ButtonCommand;
import constants.Message;
import constants.dimentions.TrueOrFalseDimension;
import constants.paths.TrueOrFalseLabelPath;
import services.QuestionService;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.TextArea;

public class TrueOrFalseUI {

    private static QuestionUI trueOrFalseUI;
    private JPanel panel;
    private JTextArea[] trueOrFalseTextArea;
    //private JLabel timerLabel;

    public static synchronized QuestionUI getInstance() {
//        if (trueOrFalseUI == null)
//            return trueOrFalseUI = new TrueOrFalseUI();
        return trueOrFalseUI;
    }

    @SuppressWarnings("deprecation")
	public JPanel getPanel() {
        //boolean enable = QuestionService.getInstance().isEnableQuestion();
        boolean user = QuestionService.getInstance().isAdminShow();
        if (panel != null)
            return panel;
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

        trueOrFalseTextArea = new JTextArea[3];
        for (int i = 0; i < 3; ++i) {
            Dimension curTextAreaDimension = TrueOrFalseDimension.textAreaDimension[i];
            trueOrFalseTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
            panel.add(trueOrFalseTextArea[i]);
        }
        if (user)
            trueOrFalseTextArea[2].enable();

        JButton[] button = new JButton[2];
        for (int i = 0; i < 2; ++i) {
            Dimension curButtonDimension = TrueOrFalseDimension.buttonDimension[i];
            String curButtonName = ButtonCommand.trueOrFalse[i];
            button[i] = new Button().button(curButtonDimension, curButtonName);
            panel.add(button[i]);
        }

        panel.add(Picture.image(dim, Message.QUESTION_BACK_GROUND_PATH.getValue()));

        return panel;
    }

    public void convertToUI(Question questionModel) {
    }

    public Question convertToModel() {
        return null;
    }

	public void resetFocus() {
		// TODO Auto-generated method stub
		
	}

	public void resetTextArea() {
		// TODO Auto-generated method stub
		
	}
}
