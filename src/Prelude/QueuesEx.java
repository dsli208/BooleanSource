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
public class QueuesEx {
    
}

class IntQueue implements Cloneable {
    public final int capacity = 100;
    private int[] data;
    private int front;
    private int rear;
    
    public IntQueue() {
        front = -1;
        rear = -1;
        data = new int[capacity];
    }
    
    public boolean isEmpty() {
        return (front == -1);
    }
    
    public void enqueue(int item) throws FullQueueException {
        if ((rear + 1) % capacity == front) //meaning if we have filled the queue (we would effectively be back at 0)
            throw new FullQueueException();
        if (front == -1) { //isEmpty()
            front = 0;
            rear = 0;
        }
        else
            rear = (rear + 1) % capacity; //makes sure that rear cannot go above 100
        data[rear] = item;
    }
    
    public int dequeue() throws EmptyQueueException {
        int answer;
        if (front == -1)
            throw new EmptyQueueException();
        answer = data[front]; //save this to the variable before returning it, it could be changed in one of the steps below...
        if (front == rear) {//meaning there is only one item in the queue
            front = -1;
            rear = -1;
        }
        else
            front = (front + 1) % capacity;
        return answer;
    }
}

class FullQueueException extends Exception {
    
}

class EmptyQueueException extends Exception {
    
}