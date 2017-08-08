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
public class StacksEx {
    
    
}

class FullStackException extends Exception {
    
}

class EmptyStackException extends Exception {
    
}
//An IntStack object
class IntStack implements Comparable {
    public final int capacity = 100;
    private int[] data;
    private int top;
    
    //IntStack methods below
    //constructor
    public IntStack() {
        top = -1;
        data = new int[capacity];
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public void push(int item) throws FullStackException{
        if (top == capacity - 1)
            throw new FullStackException();
        top++;
        data[top] = item;
    }
    public int pop(int item) throws EmptyStackException {
        //int answer;
        if (top == -1) //or isEmpty() returns true
            throw new EmptyStackException();
        //Alternative way
        /*answer = data[top];
        top--;
        return answer;*/
        return data[top--];    
    }
    public int peek() throws EmptyStackException {
        //int answer;
        if (top == -1)
            throw new EmptyStackException();
        return data[top];
    }
    @Override
    public int compareTo(Object o) {
        if (((IntStack)o).top > top)
            return 1;
        else if (((IntStack)o).top < top)
            return -1;
        return 0;
    }
}