package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Question;
import constants.ButtonCommand;
import constants.dimentions.CompleteDimension;
import constants.paths.BackGroundPicturePath;
import constants.paths.CompleteLabelPath;
import services.QuestionService;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.TextArea;

public class CompleteUI {

    private static QuestionUI completeUI;
    private JPanel panel;
    private JTextArea[] completeTextArea;
    //private JLabel timerLabel;

    public static synchronized QuestionUI getInstance() {
        //if (completeUI == null)
            //return completeUI = new CompleteUI();
        return completeUI;
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
        new CompleteDimension(screenWidth, screenHeight);

        JLabel[] label = new JLabel[3];
        for (int i = 0; i < 3; ++i) {
            Dimension curLabelDimension = CompleteDimension.labelDimension[i];
            String curLabelName = CompleteLabelPath.labelName[i];
            label[i] = Label.label(curLabelDimension, curLabelName);
            panel.add(label[i]);
        }

        completeTextArea = new JTextArea[3];
        for (int i = 0; i < 3; ++i) {
            Dimension curTextAreaDimension = CompleteDimension.textAreaDimension[i];
            completeTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
            panel.add(completeTextArea[i]);
        }
        if (user)
            completeTextArea[2].enable();

        JButton[] button = new JButton[2];
        for (int i = 0; i < 2; ++i) {
            Dimension curButtonDimension = CompleteDimension.buttonDimension[i];
            String curButtonName = ButtonCommand.complete[i];
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
