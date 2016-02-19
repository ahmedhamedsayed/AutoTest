package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Question;
import constants.ButtonCommand;
import constants.dimentions.MathProblemDimension;
import constants.paths.BackGroundPicturePath;
import constants.paths.MathProblemLabelPath;
import services.QuestionService;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.TextArea;

public class MathProblemUI {

    private static QuestionUI mathProblemUI;
    private JPanel panel;
    private JTextArea[] mathProblemTextArea;
    //private JLabel timerLabel;

    public static synchronized QuestionUI getInstance() {
//        if (mathProblemUI == null)
//            return mathProblemUI = new MathProblemUI();
        return mathProblemUI;
    }

	@SuppressWarnings({ "deprecation" })
	public JPanel getPanel() {
        //boolean enable = QuestionService.getInstance().isEnableQuestion();
        boolean user = QuestionService.getInstance().isAdminShow();
        if (panel != null)
            return panel;
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

        mathProblemTextArea = new JTextArea[16];
        for (int i = 0; i < 16; ++i) {
            Dimension curTextAreaDimension = MathProblemDimension.textAreaDimension[i];
            mathProblemTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
            panel.add(mathProblemTextArea[i]);
        }
        if (user) {
            mathProblemTextArea[2].enable();
            for (int i = 9; i < 15; ++i)
                mathProblemTextArea[i].enable();
        }

        JButton[] button = new JButton[2];
        for (int i = 0; i < 2; ++i) {
            Dimension curButtonDimension = MathProblemDimension.buttonDimension[i];
            String curButtonName = ButtonCommand.mathProblem[i];
            button[i] = new Button().button(curButtonDimension, curButtonName);
            panel.add(button[i]);
        }

        panel.add(Picture.image(dim, BackGroundPicturePath.QuestionBackGroundPath));

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
