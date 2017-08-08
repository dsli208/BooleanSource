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
public class LinkedListEx {
    public static void main(String[] args) {
        
    }
}

class IntNode {
    private int data;
    private IntNode link;
    //IntNode methods
    public IntNode(int initialData) {
        data = initialData;
        //points to another set of data
        link = null;
    }
    public int getData() {
        return data;
    }
    public IntNode getLink() {
        return link;
    }
    public void setData(int data) {
        this.data = data;
    }
    public void setLink(IntNode link) {
        this.link = link;
    }
}

class IntList {
    private IntNode head;
    private IntNode tail;
    private IntNode cursor;
    public IntList() {
        //beginning of the list
        head = null;
        //end of the list
        tail = null;
        //link pointing to one of the nodes of the list
        cursor = null;
    }
    public void addNewHead(int element) {
        IntNode newNode = new IntNode(element); //create a new IntNode object
        newNode.setLink(head); //set the current head to the link on the new node
        head = newNode; //set the head to the newNode, what was previously the head is now the link on the first node
        if (tail == null)
            tail = head;
        cursor = head;
    }
    
    //Add integer after cursor
    //This changes the linked list, adding a new IntNode and setting the cursor to point at that node
    public void addIntAfter(int element) {
        //Create a newNode object
        IntNode newNode = new IntNode(element);
        //If the cursor is null, we cannot get anything from it, so simply set all its variables to newNode
        if (cursor == null) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        else {
            //Get the link of the cursor, and set it to the link of the new node
            newNode.setLink(cursor.getLink());
            //now take the whole cursor, set its link for going through the newNode to newNode
            cursor.setLink(newNode);
            cursor = newNode; //advance cursor
            if (cursor.getLink() == null)
                tail = cursor;
        }
    }
    
    //Remove after cursor
    public void removeIntAfter() {
        if (cursor != tail) { //Essentially, if the head of the next element is not the tail of this one
            cursor.setLink(cursor.getLink().getLink()); //Set the link of the cursor IntList to not the next IntList's link, but the one after that IntList
            if (cursor.getLink() == null) {
                tail = cursor; //Else, since this is the last node, the tail will then be the same thing as the cursor
            }
        }
    }
    
    //Remove the head of the IntList
    public void removeHead() {
        if (head != null) {
            head = head.getLink(); //set the head IntNode equal to it's link, effectively removing it and setting it to the next IntNode
        }
        else { //If head IS null...
            tail = null; //there is no link then for head to get, so that means tail must be null as well since there is presumably nothing after head
        }
        cursor = head;
    }
    
    //Working with cursors
    //Advance the cursor
    public boolean advanceCursor() {
        if (cursor != tail) {
            cursor = cursor.getLink(); //if the cursor is not the same thing as the tail, we assume it has a link, so get that link to the next IntList and set it to the cursor variable
            return true; //We can return true, since we can advance the cursor
        }
        else
            return false;
    }
    
    //Reset the cursor (the link pointing to the head of the next node) to make it the head of the next node
    public void resetCursor() {
        cursor = head;
    }
    
    //Checking if the cursor has any content in the IntNode object
    public boolean isEmpty() {
        return (cursor == null);
    }
    
    //Find the length of the list
    public int listLength() {
        int answer = 0;
        IntNode nodePtr = head;
        while (nodePtr != null) { //transverse until we get null, at which point we can conclude we have transversed the list
            answer++;
            nodePtr = nodePtr.getLink();
        }
        return answer;
    }
    
    //Search the list
    public boolean listSearch(int target) {
        IntNode nodePtr = head;
        while (nodePtr == null) {
            if (target == nodePtr.getData()) {
                //we now point to the target data after we find it exists
                cursor = nodePtr;
                return true;
            }
            //if the above condition is not met, we 
            nodePtr = nodePtr.getLink();
        }
        //once the loop exits, we had exhausted the list, so return false
        return false;
    }
    
    //Set the cursor to a specific position, throws Exception?
    public boolean listPosition(int position) throws Exception {
        IntNode nodePtr = head;
        int i = 1;
        //is position greater than 0?
        if (position <= 0) {
            throw new Exception("Bad position int number");
        }
        //increases the variable i progressively, so long as i is within the position limit and the nodePtr is not null
        while (i < position && nodePtr != null) {
            nodePtr = nodePtr.getLink();
            i++;
        }
        //if the nodePtr falls null, we set cursor to the max position on the list
        if (nodePtr != null)
            cursor = nodePtr;
        return (nodePtr != null);
    }
    
    //copy an IntList
    public static IntList listCopy(IntList source) {
        IntList newList = new IntList();
        IntNode nodePtr = source.head;
        while (nodePtr != null) {
            newList.addIntAfter(nodePtr.getData());
            nodePtr = nodePtr.getLink();
        }
        return newList;
    }
    
    //additional methods below
    public int getNodeData() throws EmptyListException {
        if (cursor == null)
            throw new EmptyListException();
        return cursor.getData();
    }
    public void setNodeData(int element) throws EmptyListException {
        if (cursor == null)
            throw new EmptyListException();
        cursor.setData(element);
    }
}

class EmptyListException extends Exception {
    
}