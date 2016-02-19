package ui;

import javax.swing.JPanel;

import models.Question;
import util.shape.TimerCountDown;

public interface QuestionUI {
	
	void setTimerCountDown(TimerCountDown timerCountDown);
	
    void resetTextArea();
    
    void convertToUI(Question questionModel);
    
    JPanel getPanel();

    void moveFocus();
    
    Question convertToModel();
    
    int getMark();
}