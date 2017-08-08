package Prelude;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dsli
 */
public class BinaryTrees {
    
}

class BTNode {
    private int data;
    private BTNode left;
    private BTNode right;
    
    public BTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public BTNode getLeft() {
        return left;
    }
    public BTNode getRight() {
        return right;
    }
    public void setLeft(BTNode newNode) {
        left = newNode;
    }
    public void setRight(BTNode newNode) {
        right = newNode;
    }
    public void inorder() {
        if (left != null)
            left.inorder();
        System.out.println(data);
        if (right != null)
            right.inorder();
    }
    public void preorder() {
        System.out.println(data);
        
        if (left != null)
            left.preorder();
        
        if (right != null)
            right.preorder();
    }
    
}

