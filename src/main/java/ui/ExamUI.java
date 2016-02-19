package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import constants.ButtonCommand;
import constants.MainState;
import constants.Message;
import constants.dimentions.ExamDimension;
import constants.paths.ExamLabelPath;
import models.Exam;
import models.ExamPercentage;
import models.Question;
import services.ExamService;
import services.MainService;
import util.Converter;
import util.Dimension;
import util.Format;
import util.shape.Button;
import util.shape.Label;
import util.shape.Panel;
import util.shape.Picture;
import util.shape.ScrollPane;
import util.shape.TextArea;

public class ExamUI {

	private static ExamUI examUI;
	private JPanel panel;
	private JTextArea[] examTextArea;
	@SuppressWarnings("rawtypes")
	private JList questoinList, examList;
	private Exam exam;
	private List<Exam> exams;
	private List<Question> questions;
	private static int currentTextArea;

	public ExamUI() {
		buildPanel();
	}

	public static synchronized ExamUI getInstance() {
		if (examUI == null)
			return examUI = new ExamUI();
		return examUI;
	}

	public void resetFocus() {
		examTextArea[currentTextArea].requestFocusInWindow();
		examTextArea[currentTextArea].setCaretPosition(examTextArea[currentTextArea].getText().length());
		examTextArea[currentTextArea].getCaret().setVisible(true);
	}

	public void resetQuestionFocus() {
		questoinList.requestFocusInWindow();
	}

	public void resetExamFocus() {
		examList.requestFocusInWindow();
	}

	public JPanel getPanel() {
		return panel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void buildPanel() {
		currentTextArea = 0;
		int screenWidth = Format.screenWidth();
		int screenHeight = Format.screenHeight();
		Dimension dim = new Dimension(0, 0, screenWidth, screenHeight);
		panel = Panel.create(dim);
		new ExamDimension(screenWidth, screenHeight);

		JLabel[] label = new JLabel[5];
		for (int i = 0; i < 5; ++i) {
			Dimension curLabelDimension = ExamDimension.labelDimension[i];
			String curLabelName = ExamLabelPath.labelName[i];
			label[i] = Label.label(curLabelDimension, curLabelName);
			panel.add(label[i]);
		}

		examTextArea = new JTextArea[6];
		for (int i = 0; i < 6; ++i) {
			Dimension curTextAreaDimension = ExamDimension.textAreaDimension[i];
			examTextArea[i] = TextArea.textArea(curTextAreaDimension, 15);
			panel.add(examTextArea[i]);
		}

		JButton[] button = new JButton[5];
		for (int i = 0; i < 5; ++i) {
			Dimension curButtonDimension = ExamDimension.buttonDimension[i];
			String curButtonName = ButtonCommand.exam[i];
			button[i] = new Button().button(curButtonDimension, curButtonName);
			panel.add(button[i]);
		}

		questoinList = new JList(new String[0]);
		JScrollPane questionScrollPane = ScrollPane.scrollPane(ExamDimension.scrollPaneDimension[0], 20, questoinList);
		panel.add(questionScrollPane);

		examList = new JList(new String[0]);
		JScrollPane examScrollPane = ScrollPane.scrollPane(ExamDimension.scrollPaneDimension[1], 20, examList);
		panel.add(examScrollPane);

		panel.add(Picture.image(dim, Message.GENERAL_BACK_GROUND_PATH.getValue()));
		buildDefaultAction();
		buildQuestionAction();
		buildExamAction();
	}

	private void buildDefaultAction() {
		KeyListener defaultAction = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					++currentTextArea;
					if (currentTextArea == 6)
						currentTextArea = 0;
					resetFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					--currentTextArea;
					if (currentTextArea == -1)
						currentTextArea = 5;
					resetFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_Q) {
					resetQuestionFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_E) {
					resetExamFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S) {
					ExamService.getInstance().examDone();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ExamService.getInstance().examBack();
				}
			}
		};
		for (JTextArea textArea : examTextArea) {
			textArea.addKeyListener(defaultAction);
		}
	}

	private void buildQuestionAction() {
		KeyListener questionAction = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_D) {
					resetFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_E) {
					resetExamFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_O) {
					ExamService.getInstance().examOpenQuestion();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_D) {
					ExamService.getInstance().examDeleteQuestion();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S) {
					ExamService.getInstance().examDone();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ExamService.getInstance().examBack();
				}
			}
		};
		questoinList.addKeyListener(questionAction);
	}

	private void buildExamAction() {
		KeyListener examAction = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_D) {
					resetFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_Q) {
					resetQuestionFocus();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_E) {
					ExamService.getInstance().oldExamAdd();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S) {
					ExamService.getInstance().examDone();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ExamService.getInstance().examBack();
				}
			}
		};
		examList.addKeyListener(examAction);
	}

	@SuppressWarnings("unchecked")
	public void resetPanel() {
		for (int i = 0; i < 6; ++i)
			examTextArea[i].setText("");
		questoinList.setListData(new String[0]);
		examList.setListData(new String[0]);
	}

	public void setExam(Exam exam) {
		this.exam = exam;
		examTextArea[0].setText(exam.getName());
		examTextArea[1].setText(String.valueOf(exam.getHour()));
		examTextArea[2].setText(String.valueOf(exam.getMinute()));
		examTextArea[3].setText(String.valueOf(exam.getMark()));
		examTextArea[4].setText(String.valueOf(exam.getQuestionsNumber()));
		examTextArea[5].setText(exam.getPassword());
		setQuestions(exam.getQuestions());
	}

	public Exam getExam() {
		Exam exam = new Exam();
		if (MainService.getInstance().getCurrentState().equals(MainState.AdminUpdateExam)) {
			exam.setId(this.exam.getId());
			exam.setExamPercentages(this.exam.getExamPercentages());
			exam.setQuestions(this.exam.getQuestions());
		}
		exam.setName(examTextArea[0].getText());
		exam.setHour(Integer.parseInt(examTextArea[1].getText()));
		exam.setMinute(Integer.parseInt(examTextArea[2].getText()));
		exam.setMark(Integer.parseInt(examTextArea[3].getText()));
		exam.setQuestionsNumber(Integer.parseInt(examTextArea[4].getText()));
		exam.setPassword(examTextArea[5].getText());
		return exam;
	}

	@SuppressWarnings("unchecked")
	public void setExams(List<Exam> exams) {
		this.exams = exams;
		String[] examDescription = new String[exams.size()];
		for (int i = 0; i < exams.size(); ++i) {
			double percentage = (exams.get(i).getId() == exam.getId()) ? exam.getPercentage() : 0.0;
			for (ExamPercentage examPercentage : exam.getExamPercentages()) {
				if (examPercentage.getAnotherExamId() == exams.get(i).getId())
					percentage = examPercentage.getPercentage();
			}
			examDescription[i] = Converter.getInstance().terminate(exams.get(i).getName(), 30) + " " + String.valueOf(percentage);
		}
		int index = examList.getSelectedIndex();
		examList.setListData(examDescription);
		if (index != -1 && index < examDescription.length)
			examList.setSelectedIndex(index);
	}

	public Exam getSelectedExam() {
		if (examList.getSelectedIndex() == -1)
			return null;
		return this.exams.get(examList.getSelectedIndex());
	}

	@SuppressWarnings("unchecked")
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
		String[] questionDescription = new String[this.questions.size()];
		for (int i = 0; i < this.questions.size(); ++i)
			questionDescription[i] = this.questions.get(i).getDescription();
		int index = questoinList.getSelectedIndex();
		questoinList.setListData(questionDescription);
		if (index == -1 && index < questionDescription.length)
			questoinList.setSelectedIndex(index);
	}

	public Question getSelectedQuestion() {
		if (questoinList.getSelectedIndex() == -1)
			return null;
		return this.questions.get(questoinList.getSelectedIndex());
	}
}
