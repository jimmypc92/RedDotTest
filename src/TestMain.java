import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TestMain {
    
    JFrame testFrame;
    TestCanvas testCanvas;

    public static void main(String args[]){
        
        TestMain testMain = new TestMain();
        testMain.createFrame();
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
      //Load an image for the cursor  
        Image image = toolkit.getImage("yellow_box.png");
        
      //Create the hotspot for the cursor  
        Point hotSpot = new Point(0,0);
        
      //Create the custom cursor  
        Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Yellow_Box");
        
        testMain.testFrame.setCursor(cursor);
        
    }
    
    private void createFrame(){
      //1. Create the frame.
        testFrame = new JFrame("FrameDemo");

        //Create a Layout to hold the canvas and the options pane.
        BorderLayout bLayout = new BorderLayout();
        testFrame.setLayout(bLayout);
        
        //2. Optional: What happens when the frame closes?
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        //3. Create components and put them in the frame.
        //...create emptyLabel...
        testCanvas = new TestCanvas();
        testFrame.getContentPane().add(testCanvas,BorderLayout.CENTER);
        Thread canvasUpdater = new Thread(new CanvasUpdater(testCanvas));
        canvasUpdater.start();

        //Add the Options Panel
        testFrame.getContentPane().add(createOptionsPanel(),BorderLayout.SOUTH);
        
        //4. Size the frame.
        testFrame.pack();
        testFrame.setExtendedState( testFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        //testFrame.getContentPane().setBackground(Color.BLUE);
        
        //5. Show it.
        testFrame.setVisible(true);
    }
    
    private JPanel createOptionsPanel(){
        
        JPanel optionsPanel = new JPanel();
        
        //Offer a textfield to change the speed of the dot.
        JTextField speedTextField = new JTextField();
        speedTextField.setColumns(5);
        optionsPanel.add(speedTextField);
        
        //Add set button for the speed
        JButton speedButton = new JButton();
        speedButton.setText("Set Speed");
        speedButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                   int newSpeed = Integer.parseInt(speedTextField.getText());
                   testCanvas.setDotSpeed(newSpeed);
                }
                catch(NumberFormatException exc){
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(testFrame,
                            "That's not an integer.",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        optionsPanel.add(speedButton);
        
        
        return optionsPanel;
        
    }
    
    
}
