package ui;

import javax.swing.JPanel;

import models.Question;
import util.shape.TimerCountDown;

public interface QuestionUI {
	
	void setTimerCountDown(TimerCountDown timerCountDown);
	
    void resetTextArea();
    
    void convertToUI(Question question);
    
    JPanel getPanel();

    Question convertToModel();
    
    int getMark();
}
