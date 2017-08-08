/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit4;
import Unit2.*;
/**
 *
 * @author dsli
 */
public class IntQueueList implements Cloneable {
    private IntNode front;
    private IntNode rear;
    public IntQueueList() {
        front = null;
        rear = null;
    }
    public boolean isEmpty() {
        return (front == null);
    }
    
    public void enqueue(int item) {
        IntNode newNode = new IntNode(item);
        if (front == null) {
            front = newNode;
            rear = front;
        }
        else {
            rear.setLink(newNode);
            rear = newNode;
        }
    }
    
    public int dequeue() throws EmptyQueueException {
        int answer;
        if (front != null) {
            answer = front.getData();
            if (front.getLink() != null)
                front = front.getLink();
            else {
                front = null;
                rear = null;
            }
            return answer;
        }
        throw new EmptyQueueException();
    }
    
}
