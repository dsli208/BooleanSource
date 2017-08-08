/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit4;

/**
 *
 * @author dsli
 */
public class IntQueue implements Cloneable {
    public final int CAPACITY = 100;
    private int[] data;
    private int front;
    private int rear;
    
    public IntQueue() {
        front = -1;
        rear = -1;
        data = new int[CAPACITY];
    }
    
    public boolean isEmpty() {
        return (front == -1);
    }
    
    public void enqueue(int item) throws FullQueueException {
        if ((rear + 1) % CAPACITY == front)
            throw new FullQueueException();
        if (front == -1)  {//isEmpty()
            front = 0; rear = 0;
        }
        else
            rear = (rear + 1) % CAPACITY; //simply increments position for rear, if a circular list, then rear == capacity, rear will be at 0
        data[rear] = item;
    }
    
    public int dequeue() throws EmptyQueueException {
        int answer;
        if (front == -1) //isEmpty()
            throw new EmptyQueueException();
        answer = data[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        else front = (front + 1) % CAPACITY;
        return answer;
    }
    
}
