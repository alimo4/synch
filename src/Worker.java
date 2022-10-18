import java.util.Collections;
import java.util.Random;

public class Worker extends Thread {
    Random rand = new Random();

    private char label;
    private ConveyorBelt buffer;

    private Widget widget;
    private int widgetCount = 1;

    // messages
    private final String retrievingMsg = "Worker " + label + " is retrieving " + widget.getName() + " from the belt";
    private final String workingMsg = "Worker " + label + " is working on " + widget.getName() + " from the belt";
    private final String placingMsg = "Worker " + label + " is placing " + widget.getName() + " from the belt";

    public Worker(char label, ConveyorBelt buffer) {
        this.label = label;
        this.buffer = buffer;

        if(label == 'A') {
            widget = new Widget("widget" + widgetCount);
        }
    }

    // picking up from conveyor belt
    public void consume() {
        System.out.println(retrievingMsg);
        buffer.receive();

        //set values
        switch (label) {
            case 'B' -> widget.setMyB();
            case 'C' -> widget.setMyC();
            case 'D' -> widget.setMyD();
        }
    }

    // production rate
    public void doWork() throws InterruptedException {
        System.out.println(workingMsg);
        //sleep
        int ms = (int) (1000 * Math.random());
        Thread.sleep(ms);
    }

    // passing onto conveyor belt
    public void produce() {
        //if buffer full, wait()
        if(buffer.send(widget)) {

        }
        System.out.println(placingMsg);


        if(label == 'A') {
            widgetCount++;
        }
    }

    // begin operation
    public void run() {
        // pick up from conveyor if you aren't worker A and set values
        if(label != 'A') {
            consume();
        }

        // all workers sleep and display working message
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // place onto conveyor belt if you aren't worker D
        if(label != 'D') {
            produce();
        }
    }
}
