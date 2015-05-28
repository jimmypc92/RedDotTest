import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TestMain {
    
    JFrame testFrame;
    TestCanvas testCanvas;
    JLabel upTimeLabel;
    TimeLabel timeLabel;
    JLabel percentageLabel;
    

    public static void main(String args[]){
        
        TestMain testMain = new TestMain();
        testMain.createFrame();
        
    }
    
    private void createFrame(){
      //1. Create the frame.
        testFrame = new JFrame("Side To Side Test");

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
        
        upTimeLabel = new JLabel("0");
        testCanvas.setDisplayLabel(upTimeLabel);
        optionsPanel.add(upTimeLabel);
        
        timeLabel = new TimeLabel(TimeLabel.INITIAL_STRING);
        testCanvas.setTimeLabel(timeLabel);
        optionsPanel.add(timeLabel);
        
        percentageLabel = new JLabel("0%");
        testCanvas.setPercentageLabel(percentageLabel);
        optionsPanel.add(percentageLabel);
        
        
        return optionsPanel;
        
    }
    
    
}
