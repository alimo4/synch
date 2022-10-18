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

    private Vector queue;
    private final int MAX = 3;

    public ConveyorBelt() {
        queue = new Vector();
    }

    /*
     * This implements a non-blocking send
     */
    public boolean send(Object item) {
        if(queue.size() >= MAX) {
            return false;
        }

        queue.addElement(item);
        return true;
    }

    /*
     * This implements a non-blocking receive
     */
    public Object receive() {
        Object item;

        if (queue.size() == 0)
            return null;
        else {
            item = queue.firstElement();
            queue.removeElementAt(0);

            return item;
        }
    }
}