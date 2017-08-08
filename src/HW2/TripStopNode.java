/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW2;

/**
 * David Li
 * 110328771
 * Assignment #2
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class TripStopNode {
    /**
     * Invariants:
     * data, the <code> TripStop </code> wrapped in this node
     * next, a link to the next TripStopNode
     * prev, a link to the prev TripStopNode
     **/
    private TripStop data;
    private TripStopNode next;
    private TripStopNode prev;
    
    /**
     * Constructs and returns an Object of type TripStopNode using a TripStop to be wrapped in the created node
     * @param initData
     *      A TripStop object to be wrapped in the new TripStopNode
     * <dt> Precondition: </dt>
     *      The object cannot be null or the below exception will be thrown
     * @exception IllegalArgumentException
     *      Thrown if <code> initData </code> is nul
     * <dt> Postcondition </dt>
     *      The TripStopNode object is created with the TripStop <code> data </code> wrapped inside.  <code> next, prev </code> are still null, as they are to be set in the programs they are to be used in
     **/
    public TripStopNode(TripStop initData) throws IllegalArgumentException {
        //Set data to initData?
        if (initData == null)
            throw new IllegalArgumentException();
        this.data = initData;
        next = null;
        prev = null;
    }
    /**
     * Returns the data node
     * @return
     *      <code> data </code>, the TripStop containing data regarding the stop
     **/
    public TripStop getData() {
        return data;
    }
    /**
     * Changes the data wrapped in the TripStopNode
     * <dt> Precondition </dt>
     *      <code> newData </code> cannot be a null object
     * <dt> Postcondition </dt>
     *      <code> data </code> is set to match <code> newData </code>
     **/
    public void setData(TripStop newData) throws IllegalArgumentException {
        if (newData == null)
            throw new IllegalArgumentException();
        this.data = newData;
    }
    /**
     * Gets the next TripStopNode in the list
     * @return
     *      <code> next </code>, a TripStopNode link to the next TripStopNode
     **/
    public TripStopNode getNext() {
        return next;
    }
    /**
     * Sets <code> next </code> to match <code> newNext </code>
     * @param newNext
     *      The new TripStopNode to be set as the next TripStopNode
     * <dt> Postcondition </dt>
     *      <code> next </code> is set to <code> newNext </code>
     **/
    public void setNext(TripStopNode newNext) {
        this.next = newNext;
    }
    /**
     * Gets the previous TripStopNode in the list
     * @return
     *      <code> prev </code>, the previous TripStopNode in the list
     **/
    public TripStopNode getPrev() {
        return prev;
    }
    /**
     * Sets a new TripStopNode as the previous one in the list
     * @param newPrev
     *      The TripStopNode to be set as the previous one in the list
     * <dt> Postcondition </dt>
     *      <code> prev </code> for the given TripStopNode now takes the reference of <code> newPrev </code>
     **/
    public void setPrev(TripStopNode newPrev) {
        this.prev = newPrev;
    }
    /**
     * Returns a clone of the TripStopNode, containing a clone of the wrapped TripStop
     * @return 
     *      A cloned TripStopNode wrapping a cloned TripStop
     **/
    @Override
    public Object clone() {
        TripStopNode nodeClone = new TripStopNode((TripStop)data.clone());
        nodeClone.setNext(next);
        nodeClone.setPrev(prev);
        return (TripStopNode)nodeClone;
    }
}
