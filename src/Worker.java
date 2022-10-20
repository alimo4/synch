// Ali Mohamed
public class Worker extends Thread {

    private final char label;
    private ConveyorBelt incomingBuffer, outgoingBuffer;
    private Widget widget;
    private int widgetCount = 1;

    public Worker(char label, ConveyorBelt in, ConveyorBelt out) {
        super(String.valueOf(label));
        this.label = label;
        this.incomingBuffer = in;
        this.outgoingBuffer = out;
    }

    // Worker A widget creation
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
        if(incomingBuffer.isEmpty()){
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

    // Mimicking work via Thread sleep & increment counters
    public void doWork() throws InterruptedException {
        widgetCount++;
        widget.workUpon(label);
        System.out.println(Messages.getWorkingMsg(label, widget));

        int ms = (int) (1000 * Math.random());
        Thread.sleep(ms);
    }

    // Passing onto conveyor belt
    public void produce() throws InterruptedException {
        //send widget
        outgoingBuffer.send(widget, label);
        System.out.println(Messages.getPlacingMsg(label, widget));
    }

    // Begin operation
    public void run() {
        while(true) {
            if(widgetCount == 25)
                return;
            // Step 1 - create or consume
            if(label == 'A') {
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
