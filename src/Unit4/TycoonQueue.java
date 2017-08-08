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
public class TycoonQueue { //SOLUTION TO RECITATION 4, EXERCISE #4
    private Object[] a;
    private int front, rear; //Specifying the front and rear of the Queue
    private int count; //Objects in a
    private int arrSize = 10; //Size of a
    
    //Constructor
    public TycoonQueue() {
        count = 0; front = 0; rear = 0; a = new Object[arrSize];
    }
    
    //dequeue() method
    public Object dequeue() throws EmptyQueueException {
        if (count <= 0)
            throw new EmptyQueueException(); //Or return null;
        Object temp = a[(front + 1) % arrSize];
        a[(front + 1) % arrSize] = null;
        front = (front + 1) % arrSize;
        return temp;
    }
    
    //Returns count
    public int size() {
        return count;
    }
    
    public void enqueue(Object o) {
        if (count == 0 && front == rear) { //Empty queue, for the VERY FIRST PERSON IN LINE
            a[0] = o;
            rear = (rear + 1) % arrSize;
            count++;
        }
        else if (front != ((rear + 1) % arrSize)) { //If there is still room in the current array (e.g., rear could be at position 0 in the array as long as people have been dequeued beforehand, meaning front was moved a few positions forward in the array
            a[(rear + 1) % arrSize] = o;
            rear = (rear + 1) % arrSize;
            count++;
        }
        else { //We have reached the end of the array, but there are still people in the "front" position, meaning we need a bigger queue/array
            //rear++; //not (rear + 1) % arrSize, for we will be resizing the queue so no need to replace the front of the queue
            extendList();
            
        }
    }
    
    private void extendList() {
        Object[] temp = new Object[arrSize * 2];
        //We're not copying in the original array as is, so as to reorder the array based on where front really is in the original one
        System.arraycopy(a, front, temp, 0, (arrSize - front));
        System.arraycopy(a, 0, temp, (arrSize - rear), (rear)); //Remember, rear was incremented prior to this method's execution!
        front = 0; rear = arrSize; //reconfiguring front and rear for the new array
        arrSize = arrSize * 2; //Now changing the arrSize to reflect the new size of the array.  (NOTE: We couldn't do it before, since we needed the original value in the copying process)
        a = temp; //Change the reference of a, such that it no longer references the smaller array
    }
    
    
}
