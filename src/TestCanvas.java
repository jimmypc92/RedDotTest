import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;


public class TestCanvas extends Canvas {

    private Point redPoint;
    private int pointMovementSpeed;
    
    
    public TestCanvas() {
        super();
        this.setBackground(Color.BLUE);
        redPoint = new Point(0,0);
        pointMovementSpeed = 3;
    }

    public TestCanvas(GraphicsConfiguration arg0) {
        super(arg0);
    }

    @Override
    public void paint (Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(redPoint.x, this.getHeight()/2, 10, 10);
        redPoint.x = redPoint.x + pointMovementSpeed;
        
     }
    
    public void setDotSpeed(int speed){
        pointMovementSpeed = speed;
    }
    
}
