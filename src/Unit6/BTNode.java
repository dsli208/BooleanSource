/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit6;

/**
 * David S. Li
 * 110328771
 * Assignment #5
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class BTNode {

    private int data;
    private BTNode left;
    private BTNode right;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BTNode getLeft() {
        return left;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setRight(BTNode right) {
        this.right = right;
    }

    public BTNode(int initData) {
        data = initData;
        left = null;
        right = null;
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
    
    public void postorder() {
        if (left != null)
            left.postorder();
        if (right != null)
            right.postorder();
        System.out.println(data);
    }
    
    public void traverse() {
        if (left != null)
            left.traverse();
        if (right != null)
            right.traverse();
    }
    
    public int getRightmostData() {
        if (right == null)
            return data;
        return right.getRightmostData();
    }
}
