package util.shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import main.MainService;
import util.Converter;
import configuration.uiConfiguration.Dimension;
import configuration.uiConfiguration.Format;

public class TimerCountDown {
    private JLabel label;
    private CountdownListener countDownListener;
    private Timer timer;

    public TimerCountDown(int h, int m, int s) {
        int screenWidth = Format.screenWidth();
        int screenHeight = Format.screenHeight();
        Dimension timerDim = new Dimension(screenWidth - 290, screenHeight - 140, 280, 200);
        setLabel(TimerLabel.timerLabel(timerDim, 70, Converter.getInstance().convertToTimerFormat(h, m, s)));
		if (h != 0 || m != 0 || s != 0)
			setCountDownListener(h, m, s);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public CountdownListener getCountDownListener() {
        return countDownListener;
    }

	public void setCountDownListener(CountdownListener countdownListener) {
		this.countDownListener = countdownListener;
	}
    
    public void setCountDownListener(int h, int m, int s) {
        this.countDownListener = new CountdownListener(h, m, s);
        timer = new Timer(1000, getCountDownListener());
        timer.start();
    }

    public void stopTimer() {
    	this.timer.stop();
    }
    
    class CountdownListener implements ActionListener {
        public int hur, min, sec;

        public CountdownListener(int h, int m, int s) {
            hur = h;
            min = m;
            sec = s;
        }

        public void actionPerformed(ActionEvent arg0) {
            --sec;
            if (sec == -1) {
                --min;
                sec = 59;
            }
            if (min == -1) {
                --hur;
                min = 59;
            }
            if (hur == -1) {
            	stopTimer();
                MainService.getInstance().exit();
            } else
                getLabel().setText(Converter.getInstance().convertToTimerFormat(hur, min, sec));
        }
    }
}
