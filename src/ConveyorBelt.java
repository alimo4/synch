// Ali Mohamed
/**
 * MessageQueue.java
 *
 * This program implements the bounded buffer using message passing.
 * Note that this solutions is NOT thread-safe. A thread safe solution
 * can be developed using Java synchronization which is discussed in Chapter 7.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

import java.util.*;

public class ConveyorBelt {

    private List<Widget> queue;
    private final int MAX = 3;

    public ConveyorBelt() {
        queue = new ArrayList<>();
    }

    /*
     * This implements a non-blocking send
     */
    public synchronized void send(Widget item, char label) throws InterruptedException {
        if(isFull()) {
            System.out.println(Messages.getFullWarning(label, item));
            wait();
        }

//        queue.addElement(item);
        queue.add(item);
    }

    /*
     * This implements a non-blocking receive
     */
    public synchronized Widget receive() {
        if (queue.size() == 0)
            return null;

        Widget item;
        boolean wasFull = isFull();

        item = queue.get(0);
        queue.remove(0);

        if(wasFull)
            notify();

        return item;
    }

    public boolean isFull() {
        return queue.size() == MAX;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}