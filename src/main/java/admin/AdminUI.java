package admin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import questionPackage.question.Question;
import unit.Unit;
import util.shape.Button;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.ScrollPane;
import configuration.commandConfiguration.ButtonCommand;
import configuration.uiConfiguration.BackGroundPicturePath;
import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;
import exam.Exam;
import exam.ExamService;

public class AdminUI {

	private static AdminUI adminUI;
	private JPanel panel;
	private JList<String> adminUnitList, adminQuestionList, adminExamList;
	private JScrollPane adminUnitScrollPane, adminQuestionScrollPane;
	private List<Unit> units;
	private List<Question> questions;
	private List<Exam> exams;
	private JButton[] buttons;
	private static int currentPane;

	public static synchronized AdminUI getInstance() {
		if (adminUI == null)
			return adminUI = new AdminUI();
		return adminUI;
	}

	public void resetUnitFocus() {
		currentPane = 0;
		adminUnitList.requestFocusInWindow();
	}

	public void resetQuestionFocus() {
		currentPane = 1;
		adminQuestionList.requestFocusInWindow();
	}

	public void resetExamFocus() {
		adminExamList.requestFocusInWindow();
	}

	public JPanel getPanel() {
		if (panel != null)
			return panel;
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);

		buttons = new JButton[10];
		for (int i = 0; i < 10; ++i) {
			Dimension curButtonDimension = AdminDimension.getInstance().buttonDimension[i];
			String curButtonName = ButtonCommand.admin[i];
			buttons[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(buttons[i]);
		}

		adminUnitList = new JList<String>(new String[0]);
		adminUnitScrollPane = ScrollPane.scrollPane(AdminDimension.getInstance().scrollPaneDimension[0], 20, adminUnitList);
		panel.add(adminUnitScrollPane);

		adminQuestionList = new JList<String>(new String[0]);
		adminQuestionScrollPane = ScrollPane.scrollPane(AdminDimension.getInstance().scrollPaneDimension[0], 20, adminQuestionList);
		panel.add(adminQuestionScrollPane);
		adminQuestionScrollPane.setVisible(false);

		adminExamList = new JList<String>(new String[0]);
		JScrollPane examScrollPane = ScrollPane.scrollPane(AdminDimension.getInstance().scrollPaneDimension[1], 20, adminExamList);
		panel.add(examScrollPane);

		panel.add(Picture.image(dim, BackGroundPicturePath.GeneralBackGroundPath));
		buildDefaultAction();
		buildExamAction();
		return panel;
	}

	private void buildDefaultAction() {
		KeyListener defaultAction = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					AdminService.getInstance().adminNext();
					resetQuestionFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					AdminService.getInstance().adminPrev();
					resetUnitFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_N) {
					AdminService.getInstance().adminNew();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_U) {
					AdminService.getInstance().adminUpdate();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_D) {
					AdminService.getInstance().adminDelete();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_E) {
					resetExamFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					AdminService.getInstance().adminOut();
				}
			}
		};
		adminUnitList.addKeyListener(defaultAction);
		adminQuestionList.addKeyListener(defaultAction);
	}

	private void buildExamAction() {
		KeyListener examAction = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_A) {
					ExamService.getInstance().examAdd();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_N) {
					ExamService.getInstance().examNew();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_U) {
					ExamService.getInstance().examUpdate();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_D) {
					ExamService.getInstance().examDelete();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_Q) {
					if (currentPane == 0)
						resetUnitFocus();
					else
						resetQuestionFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					AdminService.getInstance().adminOut();
				}
			}
		};
		adminExamList.addKeyListener(examAction);
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
		adminUnitScrollPane.setVisible(true);
		adminQuestionScrollPane.setVisible(false);
		String[] unitDescription = new String[units.size()];
		for (int i = 0; i < units.size(); ++i)
			unitDescription[i] = units.get(i).getDescription();
		int index = adminUnitList.getSelectedIndex();
		adminUnitList.setListData(unitDescription);
		if (index != -1 && index < unitDescription.length)
			adminUnitList.setSelectedIndex(index);
	}

	public Unit getSelectedUnit() {
		if (adminUnitList.getSelectedIndex() == -1) {
			//Error.reportErrorMessage(Message.selectUnitError);
			return null;
		}
		return units.get(adminUnitList.getSelectedIndex());
	}

	public void setQuestions(List<Question> questions) {
		if (questions == null) {
			return;
		}
		this.questions = questions;
		adminUnitScrollPane.setVisible(false);
		adminQuestionScrollPane.setVisible(true);
		String[] questionDescription = new String[questions.size()];
		for (int i = 0; i < questions.size(); ++i)
			questionDescription[i] = questions.get(i).getDescription();
		int index = adminQuestionList.getSelectedIndex();
		adminQuestionList.setListData(questionDescription);
		if (index != -1 && index < questionDescription.length)
			adminQuestionList.setSelectedIndex(index);
	}

	public Question getSelectedQuestion() {
		if (adminQuestionList.getSelectedIndex() == -1) {
			//Error.reportErrorMessage(Message.selectQuestionError);
			return null;
		}
		return questions.get(adminQuestionList.getSelectedIndex());
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
		String[] examDescription = new String[exams.size()];
		for (int i = 0; i < exams.size(); ++i)
			examDescription[i] = exams.get(i).getName();
		int index = adminExamList.getSelectedIndex();
		adminExamList.setListData(examDescription);
		if (index != -1 && index < examDescription.length)
			adminExamList.setSelectedIndex(index);
	}

	public Exam getSelectedExam() {
		if (adminExamList.getSelectedIndex() == -1) {
			//Error.reportErrorMessage(Message.selectExamError);
			return null;
		}
		return exams.get(adminExamList.getSelectedIndex());
	}
}
