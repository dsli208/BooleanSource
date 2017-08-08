/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit3;
import Unit2.IntNode;
import java.util.EmptyStackException;
/**
 *
 * @author dsli
 */
public class IntStackList implements Cloneable {
    private IntNode top;
    public IntStackList() {
        top = null;
    }
    public boolean isEmpty() {
        return (top == null);
    }
    public void push(int item) {
        IntNode newNode = new IntNode(item);
        newNode.setLink(top);
        top = newNode;
    }
    
    public int pop() {
        int answer;
        if (top == null) //isEmpty()
            throw new EmptyStackException();
        answer = top.getData();
        top = top.getLink();
        return answer;
    }
    
    public int peek() {
        int answer;
        if (top == null) //isEmpty()
            throw new EmptyStackException();
        answer = top.getData();
        return answer;
    }
}
