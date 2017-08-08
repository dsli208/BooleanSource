/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit3;

import java.util.EmptyStackException;

/**
 *
 * @author dsli
 */
public class IntStack implements Cloneable {

    public final int CAPACITY = 100;
    private int[] data;
    private int top;

    public IntStack() {
        top = -1;
        data = new int[CAPACITY];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(int item) throws FullStackException {
        if (top == CAPACITY - 1) {
            throw new FullStackException();
        }

        top++;
        data[top] = item;
    }
    
    public int pop() {
        int answer;
        if (top == -1) //isEmpty()
            throw new EmptyStackException();
        answer = data[top];
        top--;
        return answer;
    }
    public int size() {
        return top + 1;
    }
    
    public int peek() {
        int answer;
        if (top == -1) //isEmpty
            throw new EmptyStackException();
        answer = data[top];
        return answer;
    }
    
    public Object clone() {
        IntStack clonedStack = new IntStack();
        //Don't set this to a variable, arraycopy() is a VOID METHOD
        System.arraycopy(this.data, 0, clonedStack.data, 0, CAPACITY);
        //NOTE: Private variables can only be changed directly within a class.  Since clonedStack is an instance of this class, we have direct access in this class itself
        clonedStack.top = this.top;
        return clonedStack;
    }
}

/*class Test {
    IntStack i = new IntStack();
    public void setTopOfTest(int n) {
        i.top = n;
    }
}*/