/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW5;

import java.util.List;

/**
 * David S. Li
 * 110328771
 * Assignment #5
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class TreeNode {
    /**
     * Invariants:
     * keywords, a String array containing the words that describe this tree node
     * left, a reference to the TreeNode on the left of this node
     * right, a reference to the TreeNode on the right of this node
     * keywordCount, a number denoting the length of the keywords array
     **/
    private String[] keywords;
    private TreeNode left;
    private TreeNode right;
    private int keywordCount;
    
    /**
     * Returns the keywords array
     * @return
     *      <code> keywords </code>, the array containing Strings used to describe this node
     **/
    public String[] getKeywords() {
        return keywords;
    }
    
    /**
     * Changes <code> keywords </code> to reference another array
     * @param keywords
     *      The new set of keywords to describe this node
     * <dt> Postcondition </dt>
     *      <code> keywords </code> is changed to reference the parameter array
     **/
    public void setKeywords(String[] keywords) {
        keywordCount = keywords.length;
        for (String s: keywords)
            s = s.toLowerCase();
        this.keywords = keywords;
    }
    
    /**
     * Returns left, the reference to the "no" TreeNode
     * @return
     *      <code> left </code>, the "no" TreeNode
     **/
    public TreeNode getLeft() {
        return left;
    }
    
    /**
     * Sets left to reference the given parameter
     * @param left
     *      The new "no" child of this TreeNode
     * <dt> Postcondition </dt>
     *      The left node of this TreeNode now references the <code> left </code> parameter
     **/
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Returns right, the reference to the "yes" TreeNode
     * @return
     *      <code> right </code>, the "yes" TreeNode
     **/
    public TreeNode getRight() {
        return right;
    }

    /**
     * Sets right to reference the given parameter
     * @param right
     *      The new "yes" child of this TreeNode
     * <dt> Postcondition </dt>
     *      The right node of this TreeNode now references the <code> right </code> parameter
     **/
    public void setRight(TreeNode right) {
        this.right = right;
    }
    
    /**
     * Returns true if the leaf has no left or right children
     * @return
     *      true/false, depending on whether left and right are BOTH null
     **/
    public boolean isLeaf() {
        return (left == null && right == null);
    }
    
    /**
     * Extends the <code> keywords </code> array to twice its original length
     * <dt> Postcondition </dt>
     *      A new String[] is created, twice the size of the original, and all the elements of the original array are copied to that array, before it takes the keywords reference
     **/
    public void extendKeywordsArray() {
        int ogLength = keywords.length;
        int newLength = 2 * keywords.length;
        String[] newKeywords = new String[newLength];
        System.arraycopy(keywords, 0, newKeywords, 0, ogLength);
        keywords = newKeywords;
    }
    
    /**
     * Constructs an instance of a TreeNode object
     * <dt> Postcondition </dt>
     *      A TreeNode has been initalized, <code> keywordCount </code> initialized to 0 and <code> left, right </code> initialized to null references
     **/
    public TreeNode() {
        this.keywordCount = 0;
        this.left = null; this.right = null;
    }
    
    /**
     * Returns a String description of the keywords array
     * @return
     *      s, a String containing all the elements in keywords
     **/
    public String keywordsToString() {
        String s = "";
        for (String keyword: keywords) {
            s += keyword + ", ";
        }
        s += "\n";
        return s;
    }
    
    /**
     * Returns a String description of the TreeNode as a whole
     * @return
     *      A String description of this node's keywords, the left child's keywords, and the right child's keywords
     **/
    @Override
    public String toString() {
        String s = "";
        if (!isLeaf())
            s += "This node: " + keywordsToString() + "\n";
        if (left != null)
            s += "The left child: " + left.keywordsToString();
        if (right != null)
            s += "The right child: " + right.keywordsToString();
        return s;
    }
}
