/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW3;

/**
 * David S. Li
 * 110328771
 * Assignment #3
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class JSNode {
    /**
     * Invariants:
     * b, a BlockType enum containing a '(', '{', '"', or "for" (denoting parentheses, braces, quotations, or for loops that need to be closed accordingly)
     * link, a reference to the next JSNode below this on the JSStack
     **/
    private BlockType b;
    JSNode link;
    
    /**
     * Returns the BlockType wrapped in this JSNode
     * @return b
     *      The BlockType that is in this JSNode
     **/
    public BlockType getData() {
        return b;
    }
    
    /**
     * Sets b to match the given parameter (NOTE: This, and <code> setLink() </code> are never invoked over the course of this program)
     * @param b
     *      The BlockType that is to be wrapped in this JSNode, replacing the current BlockType
     **/
    public void setData(BlockType b) {
        this.b = b;
    }
    
    /**
     * Returns the link of this JSNode
     * @return 
     *      <code> link </code>, a reference to the next JSNode down the JSSTack
     **/
    public JSNode getLink() {
        return link;
    }
    
    /**
     * Sets the link of this JSNode to the JSNode provided in the parameter (Note: as with <code> setData() </code> above, this method is never invoked in the JavascriptFormatter, as for a linked list stack, the nodes are not changed)
     * <dt> Postcondition </dt>
     *      <code> this.link </code> now references the <code> link </code> in the parameter of this method
     **/
    public void setLink(JSNode link) {
        this.link = link;
    }
    
    /**
     * Creates and returns a new instance of JSNode
     * @param b
     *      The BlockType to wrap in this node
     * <dt> Postcondition </dt>
     *      b is intialized to the given parameter, link is initialized to a null reference
     **/
    public JSNode(BlockType b) {
        this.b = b;
        link = null;
    }
}
