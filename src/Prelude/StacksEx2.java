/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prelude;
import Prelude.LinkedListEx;
import Prelude.StacksEx;
/**
 *
 * @author dsli
 */
public class StacksEx2 {
    
}

//IntStack with lists
class IntStack2 implements Cloneable {
    private IntNode top;
    public IntStack2() {
        top = null;
    }
    public boolean isEmpty() {
        return (top == null);
    }
    public void push(int item) {
        IntNode newNode = new IntNode(item);
        //set the link of newNode to (the current) top, if popped, we can make that the top again
        newNode.setLink(top);
        //make this newNode the new top
        top = newNode;
    }
    public int pop() throws EmptyStackException {
        int answer;
        if (top == null) {
            throw new EmptyStackException();
        }
        answer = top.getData();
        top = top.getLink();
        return answer;
    }
    public int peek() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.getData();
    }
    public int compareTo(Object o) {
        if (((IntStack2)o).getStackLength() > getStackLength())
            return -1;
        else if (((IntStack2)o).getStackLength() < getStackLength())
            return 1;
        return 0;
    }
    public int getStackLength() {
        int length = 0;
        IntNode nodePtr = top;
        while (nodePtr != null) {
            length++;
            nodePtr = nodePtr.getLink();
        }
        return length;
    }
}