/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW4;

/**
 * David S. Li
 * 110328771
 * Assignment #4
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class DownloadQueue {
    /**
     * Invariants:
     * front: Reference to the DownloadNode denoted as the front of the queue
     * rear: Reference to the DownloadNode denoted as the rear of the queue
     **/
    private DownloadNode front;
    private DownloadNode rear;
    
    /**
     * Creates an instance of a DownloadQueue object
     * <dt> Postcondition </dt>:
     *      As the Queue is initialized empty, front and rear are both set to null references
     **/
    public DownloadQueue() {
        front = null;
        rear = null;
    }
    
    /**
     * Appends a new node containing a newly created DownloadJob to the rear of the DownloadQueue
     * @param newJob
     *      A DownloadJob object to be wrapped in a DownloadNode that will be appended to the rear of the DownloadQueue
     * <dt> Postcondition </dt>
     *      <code> newJob </code> is wrapped into a new DownloadNode that either becomes the first node (only if the queue is empty/front is null) or the rear of the queue
     **/
    public void enqueue(DownloadJob newJob) {
        DownloadNode newNode = new DownloadNode(newJob);
        if (front == null) {
            front = newNode; rear = newNode;
        }
        else {
            rear.setLink(newNode);
            rear = newNode;
        }
    }
    
    /**
     * Removes the DownloadJob at the front of the queue and returns it
     * @exception EmptyQueueException
     *      Thrown if there are no nodes in the queue
     * @return
     *      nextJob, the DownloadJob data extracted from the DownloadNode at the front of the queue
     **/
    public DownloadJob dequeue() throws EmptyQueueException {
        if (front == null) {
            throw new EmptyQueueException();
        }
        DownloadJob nextJob = front.getData();
        if (front.getLink() != null)
            front = front.getLink();
        else {
            front = null; rear = null;
        }
        return nextJob;
    }
    
    /**
     * Returns the DownloadJob at the front of the queue without modifying it
     * @exception 
     *      Thrown if the queue is empty
     * @return
     *      <code> nextJob </code>, the DownloadJob data extracted from the front DownloadNode
     **/
    public DownloadJob peek() throws EmptyQueueException {
        if (front == null)
            throw new EmptyQueueException();
        DownloadJob nextJob = front.getData();
        return nextJob;
    }
    
    /**
     * Returns true or false depending on if the queue is empty
     * @return
     *      True or false, depending on if the queue was empty
     **/
    public boolean isEmpty() {
        return (front == null);
    }
    
    /**
     * Returns a String representation of the DownloadJobs in this queue
     * @return
     *      A String containing an abbreviation of the data in each DownloadNode
     **/
    @Override
    public String toString() {
        String s = "";
        DownloadNode nodePtr = front;
        while (nodePtr != null) {
            s += nodePtr.getData().toQueueString();
            nodePtr = nodePtr.getLink();
        }
        return s;
    }
}
