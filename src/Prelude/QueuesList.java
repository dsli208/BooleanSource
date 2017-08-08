/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prelude;

/**
 *
 * @author dsli
 */
public class QueuesList {
}

class IntQueue2 implements Cloneable {

    private IntNode front;
    private IntNode rear;

    public IntQueue2() {
        front = null;
        rear = null;
    }
    
    public boolean isEmpty() {
        return (front == null);
    }
    
    public void enqueue(int item) {
        IntNode newNode = new IntNode(item);
        if (front == null) { //isEmpty() is true
            //establish the front of the list
            front = newNode;
            //make the rear the same thing as the front
            rear = front;
        }
        else {
            //make a new end of the list
            rear.setLink(newNode);
            //set the rear variable to it, now it is designated as the rear
            rear = newNode;
        }
    }
    
    public int dequeue() throws EmptyQueueException {
        int answer;
        if (front == null)
            throw new EmptyQueueException();
        answer = front.getData();
        front = front.getLink();
        if (front == null)
            rear = null; //makes the list empty once again
        return answer;
    }
}
