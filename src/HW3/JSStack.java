/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * David S. Li
 * 110328771
 * Assignment #3
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class JSStack {
    /**
     * Invariants: f, a File Object that represents a text file containing a Stack Trace (more info below)
     * stackTraceWriter, a PrintWriter Object that writes to the stackTrace file
     * head, a JSNode representing the top of the JSStack
     **/
    //File f = new File("src/HW3/StackTrace");
    //PrintWriter stackTraceWriter;
    private JSNode head;

    /**
     * Returns an instance of JSStack
     * @exception FileNotFoundException
     *      Thrown if the File f does not exist
     * <dt> Postcondition </dt>
     *      head is initialized to a null JSNode, and stackTraceWriter is initialized as a PrintWriter object
     **/
    public JSStack() throws FileNotFoundException {
        head = null;
        //stackTraceWriter = new PrintWriter(f);
        //stackTraceWriter.print("(STACK TRACE OF THE JAVASCRIPTFORMATTER FOR DEBUGGING REFERENCE).");
    }
    
    /**
     * Pushes a new JSNode (that wraps a BlockType parameter) onto the top of the JSStack
     * @param b
     *      A BlockType enum that is to be wrapped into a JSNode that is placed on the top of the stack
     * <dt> Postcondition </dt>
     *      <code> newNode </code>, JSNode Object is created to wrap b before being placed at the top of the stack.  Following this, the <code> printStackTrace() </code> method is invoked to print to f a record of what the stack is following this method
     *      Additionally, after linking to the current head, newNode assumes the head reference
     **/
    public void push(BlockType b) {
        JSNode newNode = new JSNode(b);
        if (head != null) //Not an Empty list
        {
            newNode.setLink(head);
        }
        head = newNode;
        printStackTrace();
    }
    
    /**
     * Returns the top JSNode (head) on the JSStack, head (what was previously the top of the stack) is set to it's link
     * @exception EmptyListException
     *      Thrown if there are no JSNodes on the JSStack (if head is null)
     * @return 
     *      <code> nodeToPop </code>, a JSNode that has taken on the reference of what was the head/top of the list
     * <dt> Postcondition </dt>
     *      If the link of head is null (meaning head was the only JSNode in the JSStack), head becomes null and the stack is empty.  Else the link of head (the JSNode that was below head) assumes the head reference
     **/
    public JSNode pop() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException();
        }
        JSNode nodeToPop = head;
        if (head.getLink() != null) {
            head = head.getLink();
        } else {
            head = null;
        }
        printStackTrace();
        return nodeToPop;
    }
    
    /**
     * Returns the top JSNode (head) without modifying the JSStack
     * @exception EmptyListException
     *      Thrown if the JSStack is empty (head is null)
     * @return
     *      <code> head </code>, the top of the JSStack (IF head is not null, otherwise the above exception is thrown)
     **/
    public JSNode peek() throws EmptyListException {
        if (head != null) {
            return head;
        }
        throw new EmptyListException();
    }
    
    /**
     * Returns true or false depending on whether the JSStack is empty (head is null)
     **/
    public boolean isEmpty() {
        return (head == null);
    }
    
    /**
     * This is a method created for debugging purposes.  Each time <code> pop() </code> or <code> push() </code> is invoked, the printWriter <code> stackTraceWriter </code> writes a line to <code> f </code> denoting what the modified JSStack is
     * <dt> Postcondition </dt>
     *      <code> stackTraceWriter </code> will have written another line with the String <code> stackTrace </code> to the File <code> f </code>.  <code> stackTrace </code> represents what is in the stack, with the leftmost symbol representing the head
     **/
    public void printStackTrace() {
        String stackTrace = "";
        JSNode nodePtr = head;
        while (nodePtr != null) {
            BlockType b = nodePtr.getData();
            switch (b) {
                case FOR:
                    stackTrace += " for ";
                    break;
                case PAREN:
                    stackTrace += " ( ";
                    break;
                case BRACE:
                    stackTrace += " { ";
                    break;
                case QUOTE:
                    stackTrace += " \" ";
                    break;
            }
            nodePtr = nodePtr.getLink();
        }
        if (stackTrace.equals("")) {
            //stackTraceWriter.println("Empty stack");
        } else {
            //stackTraceWriter.println(stackTrace);
        }
    }
    /**
     * At the end of formatting the inputted code, close <code> stackTraceWriter </code> so that all stace traces can be properly printed to <code> f </code>
     **/
    public void closePrintWriter() {
        //stackTraceWriter.close();
    }
}
