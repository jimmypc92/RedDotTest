import java.awt.Canvas;


public class CanvasUpdater implements Runnable{

    private TestCanvas canvas;
    
    public CanvasUpdater(TestCanvas c) {
        canvas = c;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(17);
                canvas.performTestLogic();
                canvas.moveTheDot();
                canvas.repaint();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
