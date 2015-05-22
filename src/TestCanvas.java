import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;


public class TestCanvas extends Canvas implements MouseMotionListener {

    private Point redPoint;
    private int redDotRadius;
    private Point cursorPos;
    private int pointMovementSpeed;
    private int cursorWidth;
    private int cursorHeight;
    private final int LEFT = -1;
    private final int RIGHT = 1;
    private int currDirection;
    private JLabel displayLabel;
    private Point topLeftOfCursor;
    private int upTime;
    private int continuousUpTime;
    private boolean isUpTimeContinuous;
    private int continuousThreshold;
    private int maximumContinuousThreshold;
    
    
    public TestCanvas() {
        super();
        this.setBackground(Color.BLUE);
        redPoint = new Point(0,0);
        pointMovementSpeed = 3;
        currDirection = RIGHT;
        topLeftOfCursor = new Point();
        redDotRadius = 30;
        continuousUpTime = 0;
        isUpTimeContinuous = false;
        continuousThreshold = 100;
        maximumContinuousThreshold = 500;
        
        upTime = 0;
        setupCursor();
        setupListener();
    }

    public TestCanvas(GraphicsConfiguration arg0) {
        super(arg0);
    }

    @Override
    public void paint (Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(redPoint.x, redPoint.y, redDotRadius, redDotRadius);
        g.setColor(Color.YELLOW);
        g.drawRect(cursorPos.x-(cursorWidth/2), cursorPos.y-(cursorHeight/2), cursorWidth, cursorHeight);
        
     }
    
    public void setDotSpeed(int speed){
        pointMovementSpeed = speed;
    }
    
    private void setupCursor(){
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
        
        //Load an image for the cursor  
          Image image = toolkit.getImage(CursorUtils.transparentPath);
          
        //Create the hotspot for the cursor  
          Point hotSpot = new Point(0,0);
          cursorWidth = 96;
          cursorHeight = 48;
          cursorPos = MouseInfo.getPointerInfo().getLocation();
          
        //Create the custom cursor  
          Cursor cursor = toolkit.createCustomCursor(image, hotSpot, CursorUtils.transparentName);
          this.setCursor(cursor);
    }
    
    public void moveTheDot(){
    	if(!isPointInBounds(redPoint.x)){
    		changeDirection();
    	}
    	redPoint.x += pointMovementSpeed * currDirection;
    	redPoint.y = 30; //this.getHeight()/2;
    }
    
    private boolean isPointInBounds(int x){
    	if(x > this.getWidth()){
    		return false;
    	}
    	if(x<0){
    		return false;
    	}
    	return true;
    }
    
    private void changeDirection(){
    	if(currDirection == RIGHT){
    		currDirection = LEFT;
    		return;
    	}
    	currDirection = RIGHT;
    }
    
    private boolean isCursorOverDot(){
    	topLeftOfCursor.x = cursorPos.x-(cursorWidth/2);
    	topLeftOfCursor.y = cursorPos.y-(cursorHeight/2);
    	if(redPoint.y < topLeftOfCursor.y || redPoint.y > (topLeftOfCursor.y + cursorHeight)){
    		return false;
    	}
    	if(redPoint.x < topLeftOfCursor.x || redPoint.x > (topLeftOfCursor.x + cursorWidth)){
    		return false;
    	}
    	return true;
    }
    
    private void incrementContinuousUpTime(){
    	if(isUpTimeContinuous){
    		continuousUpTime ++;
    	}
    }
    
    private boolean isContinuousUpTimeOverThreshold(){
    	return continuousUpTime > continuousThreshold;
    }
    
    private void setupListener(){
    	this.addMouseMotionListener(this);
    }
    
    public void performTestLogic(){
    	if(isCursorOverDot()){
    		isUpTimeContinuous = true;
    		upTime++;
    		incrementContinuousUpTime();
    		incrementDisplayLabel();
    		if(isContinuousUpTimeOverThreshold() && isPointInBounds(redPoint.x)){
    			if(shouldDotChangeDirection()){
    				changeDirection();
        			continuousUpTime = 0;
    			}
    		}
    	}
    	else{
	    	isUpTimeContinuous = false;
	    	continuousUpTime = 0;
    	}
    }
    
    private boolean shouldDotChangeDirection() {
    	if(continuousUpTime < continuousThreshold) {
    		return false;
    	}
    	else if(continuousUpTime < maximumContinuousThreshold) {
    		int setThreshDiff = (maximumContinuousThreshold - continuousThreshold);
    		int curUpTimeDiff = (maximumContinuousThreshold - continuousUpTime);
    		float probFactor = (float)(setThreshDiff - curUpTimeDiff) / (float)setThreshDiff;
    		if(Math.random() < probFactor){
    			return true;
    		}
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    private void incrementDisplayLabel(){
    	if(this.displayLabel != null){
    		displayLabel.setText(Integer.toString(upTime));
    	}
    }
    
    public void setDisplayLabel(JLabel label){
    	this.displayLabel = label;
    }

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		cursorPos = arg0.getLocationOnScreen();
		this.invalidate();
	}
}
