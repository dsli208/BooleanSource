/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit2;

/**
 *
 * @author dsli
 */
public class List {
    private Node head;
    private Node tail;
    private Node cursor;
    
    public void addNewHead(Object element) {
        Node newNode = new Node(element);
        newNode.setLink(head);
        head = newNode;
        if (tail == null)
            tail = head;
        cursor = head;
    }
    
    public boolean listSearch(Object target) {
        Node nodePtr = head;
        while (nodePtr != null) {
            if (target.equals(nodePtr.getData())) {
                cursor = nodePtr;
                return true;
            }
        }
        return false;
    }
}
