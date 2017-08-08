/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit6;

/**
 *
 * @author dsli
 */
public class BinarySearchTree {
    private BTNode root;
    public BinarySearchTree() {
        root = null;
    }
    public boolean isEmpty() {
        return (root == null);
    }
    public void inorder() {
        if (root != null)
            root.inorder(); //NOT a recursive call, just shows the root is there and calls the inorder method in the BTNode class ("helper" method)
    }
    
    public void preorder() {
        if (root != null)
            root.preorder();
    }
    
    public void postorder() {
        if (root != null)
            root.postorder();
    }
    
    public void insert(int item) {
        BTNode newNode;
        BTNode cursor;
        boolean done = false;
        if (root == null) { //isEmpty()
            newNode = new BTNode(item);
            root = newNode;
        }
        else {
            cursor = root;
            while (!done) {
                if (item < cursor.getData()) {
                    if (cursor.getLeft() == null) {
                        newNode = new BTNode(item);
                        cursor.setLeft(newNode);
                        done = true;
                    }
                    else cursor = cursor.getLeft();
                }
                else if (item > cursor.getData()) {
                    if (cursor.getRight() == null) {
                        newNode = new BTNode(item);
                        cursor.setRight(newNode);
                        done = true;
                    }
                    else cursor = cursor.getRight();
                }
                else //Meaning we have EQUAL values, so item does not need to be inserted
                    done = true;
            }
        }
    }
    
    public boolean remove(int item) { //the "boolean" part denotes if the item exists or not
        BTNode cursor = root;
        BTNode parentOfCursor = null;
        while (cursor != null && cursor.getData() != item) {
            parentOfCursor = cursor;
            cursor = (item < cursor.getData() ? cursor.getLeft() : cursor.getRight());
        }
        if (cursor == null) return false; //Denote isEmpty() or denotes that the search led to a null item (meaning it does not exist)
        else { //Figure out how to rearrange the links
            if (cursor == root && cursor.getLeft() == null)
                root = root.getRight();
            else if (cursor != root && cursor.getLeft() == null) {
                if (cursor == parentOfCursor.getLeft())
                    parentOfCursor.setLeft(cursor.getRight());
                else
                    parentOfCursor.setRight(cursor.getRight());
            }
            else {
                cursor.setData(cursor.getLeft().getRightmostData());
            }
            return true;
        }
    }
    
    
}
