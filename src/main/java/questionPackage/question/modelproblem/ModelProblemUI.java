package questionPackage.question.modelproblem;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import questionPackage.question.Question;
import questionPackage.question.QuestionService;
import questionPackage.question.QuestionUI;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Table;
import util.shape.TextArea;
import configuration.commandConfiguration.ButtonCommand;
import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;

public class ModelProblemUI {

    private static QuestionUI modelProblemUI;
    private JPanel panel;
    private JTextArea[] modelTextArea;
    //private JTable modelTable;

    public ModelProblemUI() {
        modelTextArea = new JTextArea[2];
    }

    public static synchronized QuestionUI getInstance() {
//        if (modelProblemUI == null)
//            return modelProblemUI = new ModelProblemUI();
        return modelProblemUI;
    }

    public JPanel getPanel() {
        boolean enable = QuestionService.getInstance().isAdminEdit();
        boolean user = QuestionService.getInstance().isAdminShow();
        if (panel != null)
            return panel;
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

        for (int i = 0; i < 2; ++i) {
            Dimension curTextAreaDimension = ModelProblemDimension.textAreaDimension[i];
            modelTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
            panel.add(modelTextArea[i]);
        }

        Dimension TableDimension = ModelProblemDimension.tableDimension[0];
        JTable table = Table.table(TableDimension, 15, 6, 6, enable, user);
        panel.add(table);

        JButton[] button = new JButton[2];
        for (int i = 0; i < 2; ++i) {
            Dimension curButtonDimension = ModelProblemDimension.buttonDimension[i];
            String curButtonName = ButtonCommand.model[i];
            button[i] = new Button().button(curButtonDimension, curButtonName);
            panel.add(button[i]);
        }
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
