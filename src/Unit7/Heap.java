/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit7;

/**
 *
 * @author dsli
 */
public class Heap {
    private int[] data;
    private int heapSize;
    private int maxSize;
    public Heap(int maximumSize) {
        if (maximumSize < 1) maxSize = 100;
        else maxSize = maximumSize;
        data = new int[maxSize];
        heapSize = 0;
    }
    public boolean isEmpty() {
        return (heapSize == 0);
    }
    public boolean isFull() {
        return (heapSize == maxSize);
    }
    public void insert(int item) throws Exception {
        int position;
        if (isFull()) throw new Exception();
        heapSize++;
        data[heapSize - 1] = item;
        position = heapSize - 1;
        while (position > 0 && data[position] > data[(position - 1) / 2]) {
            swap(position, (position - 1) / 2);
            position = (position - 1) / 2;
        } 
    }
    public int remove() throws Exception{
        int answer;
        if (isEmpty()) throw new Exception();
        answer = data[0];
        data[0] = data[heapSize - 1];
        heapSize--;
        fixheap();
        return answer;
    }
    
    private void fixheap() {
        int position = 0; int childPos;
        while (position * 2 + 1 < heapSize) {
            childPos = position * 2 + 1;
        }
        
    }
    
    public void swap(int fromPosition, int toPosition) {
        int temp = data[fromPosition];
        data[fromPosition] = data[toPosition];
        data[toPosition] = temp;
    }
}
