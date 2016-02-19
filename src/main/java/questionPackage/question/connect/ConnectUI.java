package questionPackage.question.connect;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import questionPackage.question.Question;
import questionPackage.question.QuestionService;
import questionPackage.question.QuestionUI;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.TextArea;
import configuration.commandConfiguration.ButtonCommand;
import configuration.uiConfiguration.BackGroundPicturePath;
import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;

public class ConnectUI {

    private static QuestionUI connectUI;
    private JPanel panel;
    private JTextArea[] connectTextArea;
    //private JLabel timerLabel;

    public static synchronized QuestionUI getInstance() {
//        if (connectUI == null)
//            return connectUI = new ConnectUI();
        return connectUI;
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
        new ConnectDimension(screenWidth, screenHeight);

        JLabel[] label = new JLabel[10];
        for (int i = 0; i < 10; ++i) {
            Dimension curLabelDimension = ConnectDimension.labelDimension[i];
            String curLabelName = ConnectLabelPath.labelName[i];
            label[i] = Label.label(curLabelDimension, curLabelName);
            panel.add(label[i]);
        }

        connectTextArea = new JTextArea[19];
        for (int i = 0; i < 19; ++i) {
            Dimension curTextAreaDimension = ConnectDimension.textAreaDimension[i];
            connectTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
            panel.add(connectTextArea[i]);
        }
        if (user)
            for (int i = 13; i < 19; ++i)
                connectTextArea[i].enable();

        JButton[] button = new JButton[2];
        for (int i = 0; i < 2; ++i) {
            Dimension curButtonDimension = ConnectDimension.buttonDimension[i];
            String curButtonName = ButtonCommand.connect[i];
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
