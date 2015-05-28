import javax.swing.Icon;
import javax.swing.JLabel;


public class TimeLabel extends JLabel {

	private int hours;
	private int minutes;
	private int seconds;
	public final static String INITIAL_STRING = "Hours: 0, Minutes: 0, Seconds: 0.";
	
	public TimeLabel() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	
	public void incrementTimer(){
		incrementSeconds();
	}
	
	public void incrementTimerAndDisplay(){
		this.incrementTimer();
		this.setText(this.toString());
	}
	
	private void incrementSeconds(){
		if(seconds != 60){
			seconds++;
		} else {
			seconds = 0;
			incrementMinutes();
		}
	}
	
	private void incrementMinutes(){
		if(minutes != 60){
			minutes++;
		} else {
			minutes = 0;
			incrementHours();
		}
	}
	
	private void incrementHours(){
		hours++;
	}
	
	@Override
	public String toString(){
		return String.format("Hours: %d, Minutes: %d, Seconds: %d.", hours,minutes,seconds);
	}

	public TimeLabel(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TimeLabel(Icon arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TimeLabel(String arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public TimeLabel(Icon arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public TimeLabel(String arg0, Icon arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

}
