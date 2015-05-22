import java.awt.Canvas;


public class CanvasUpdater implements Runnable{

    private Canvas canvas;
    
    public CanvasUpdater(Canvas c) {
        canvas = c;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(100);
                canvas.repaint();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
