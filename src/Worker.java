import java.util.Random;

public class Worker extends Thread {
    Random rand = new Random();

    private char label;
    private ConveyorBelt incomingBuffer, outgoingBuffer;

    private Widget widget;
    private int widgetCount = 1;

    // info messages
    private String retrievingMsg;
    private String workingMsg;
    private String placingMsg;
    // warning messages
    private String fullWarning;
    private String emptyWarning;

    public Worker(char label, ConveyorBelt in, ConveyorBelt out) {
        super(String.valueOf(label));
        this.label = label;
        this.incomingBuffer = in;
        this.outgoingBuffer = out;

//        if(label == 'A' && widgetCount <= 24) {
//            widget = new Widget("widget" + widgetCount);
//            setMessages();
//        }
    }

    private void createWidget() {
        //extra failsafe
        if(label == 'A') {
            widget = new Widget("widget" + widgetCount);
        }
    }

    /**
     * Attempt consume operation
     * @return whether consume was successful
     */
    public boolean consume() {
        System.out.print("");
//        System.out.println("___________");
//        incomingBuffer.displayContents(label);
        if(incomingBuffer.isEmpty()){
//            System.out.println(emptyWarning);
            return false;
        }

        //pickup widget
        widget = incomingBuffer.receive();
        System.out.println(Messages.getRetrievingMsg(label, widget));

        //set values
        switch (label) {
            case 'B' -> widget.setMyB();
            case 'C' -> widget.setMyC();
            case 'D' -> widget.setMyD();
        }
        return true;
    }

    // production rate
    public void doWork() throws InterruptedException {
        System.out.println(Messages.getWorkingMsg(label, widget));
        widgetCount++;
        //simulate work via sleep
        int ms = (int) (1000 * Math.random());
        Thread.sleep(ms);
    }

    // passing onto conveyor belt
    public void produce() throws InterruptedException {
        //send widget
        outgoingBuffer.send(widget, label);
        System.out.println(Messages.getPlacingMsg(label, widget));

        //increment widget count
//        if(label == 'A') {
//            widgetCount++;
//        }
    }

    // begin operation
    public void run() {
        while(true) {
            if(widgetCount == 25)
                return;
//            if(label == 'B')
//                System.out.println("Widget count: " +widgetCount);
            // Step 1 - create or consume
            if(label == 'A') {
//                if(widgetCount == 24) {
//                    System.out.println(widgetCount + " widgets created!");
//                    return;
//                }
                createWidget();
            }else {
                System.out.println(Messages.getEmptyWarning(label));
                while(true) {
                    if(consume())
                        break;
                }
            }

            // Step 2 - mimic doing work by thread sleeping
            try {
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Step 3 - produce
            if (label != 'D') {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
