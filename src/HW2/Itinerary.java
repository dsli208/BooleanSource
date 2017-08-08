/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW2;

/**
 *
 * David Li
 * 110328771
 * Assignment #2
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class Itinerary {
    /*
    * Invariants:
    * TripStopNode head represents the first TripStopNode in the list
    * TripStopNode tail represents the last TripStopNode in the list
    * TripStopNode cursor represents a specified node in the list - that TripStopNode can either be removed or a new TripStopNode inserted before it
    * count represents the number of TripStopNodes in the list
    * */
    private TripStopNode head;
    private TripStopNode tail;
    private TripStopNode cursor;
    private int count;

    /**
     * Returns an instance of an Itinerary object, with an empty TripStopNode list
     * **/
    public Itinerary() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Returns the number of TripStopNodes in the list
     * @return
     *  Returns the total number of TripStopNodes in the list
     **/
    public int getStopsCount() {
        return count;
    }
    /**
     * Returns the sum of the distances of each TripStop in the list
     * @return 
     *  <code> totDistance </code> the sum of the distances in each <code> TripStop </code> in the list
     **/
    public int getTotalDistance() {
        int totDistance = 0;
        TripStopNode nodeEvaluated = head;
        for (int i = 0; i < count; i++) {
            totDistance += nodeEvaluated.getData().getDistance();
            nodeEvaluated = nodeEvaluated.getNext();
        }
        return totDistance;
    }
    
    /**
     * Returns the <code> TripStop </code> wrapped in the node in the list
     * @return 
     *      The data referenced in the <code> TripStopNode </code>
     **/
    public TripStop getCursorStop() {
        if (cursor == null) {
            return null;
        }
        return cursor.getData();
    }
    
    /**
     * Sets the cursor to <code> head </code>
     * <dt> Postcondition </dt>
     *      <code> cursor </code> is now at the head of the list
     **/
    public void resetCursorToHead() {
        if (head != null) {
            cursor = head;
        } else {
            cursor = null;
        }
    }
    /**
     * Moves the cursor one position forward in the list
     * 
     * @exception EndOfItineraryException
     *      If the reference for <code> cursor </code> is the same as <code> tail </code>, this means we are at the end of the list and there is no further position to advance to
     * <dt> Postcondition </dt>
     *      <code> cursor </code> is now one <code> TripStopNode </code> position ahead of what it usually was
     * 
     **/
    public void cursorForward() throws EndOfItineraryException {
        if (cursor == tail) {
            throw new EndOfItineraryException();
        }
        cursor = cursor.getNext();
    }
    
    /**
     * Moves the cursor one position backward in the list
     * 
     * @exception EndOfItineraryException
     *  If the reference to <code> cursor </code> matches that of <code> head </code>, we are at the beginning of the list and have no node to advance to
     * <dt> Postcondition </dt>
     *      <code> cursor </code> is now at one <code> TripStopNode </code> previous relative to where it was before
     **/
    public void cursorBackward() throws EndOfItineraryException {
        if (cursor == head) {
            throw new EndOfItineraryException();
        }
        cursor = cursor.getPrev();
    }
    
    /**
     * Inserts a new <code> TripStopNode </code> before the current cursor
     * @param newStop
     *  A <code> TripStop </code> object that is desired to be inserted before the cursor
     * @exception IllegalArgumentException
     *  Thrown if <code> newStop </code> is a null Object
     * <dt> Postcondition </dt>
     *  <code> newStop </code> is wrapped in a newly created <code> TripStopNode </code> within the method itself.  This <code> TripStopNode </code> is subsequently placed before the cursor.  Links are created
     *  if it is in the middle of an existing list, connecting this TripStopNode to what is before and after it in the list.  If the list if empty <code> head, tail, and cursor </code> are set to this node.
     *  <code> count </code> is also incremented taking note of this addition.
     **/
    public void insertBeforeCursor(TripStop newStop) throws IllegalArgumentException {
        if (newStop == null) {
            throw new IllegalArgumentException();
        }
        TripStopNode newNode = new TripStopNode(newStop);
        //If there are no nodes in this list yet....
        if (cursor == null) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        //If there is already a TripStopNode behind the desired node for which newNode is to be assigned between...
        else if (cursor.getPrev() != null) {
            newNode.setPrev(cursor.getPrev());
            newNode.setNext(cursor);
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
        } //If getPrev is null, meaning cursor is at the head of the object
        else { //meaning cursor.getPrev() is null, meaning we are at the head
            cursor.setPrev(newNode);
            newNode.setNext(cursor);
            head = newNode;
        }
        count++;
    }
    
    /**
     * Inserts a new TripStopNode object containing TripStop data, but after the tail
     * @param newStop
     *  The TripStop object desired to be added to the end of this Itinerary
     * 
     * @exception IllegalArgumentException
     *  Thrown if <code> newStop </code> is a null Object
     * 
     * <dt> Postcondition: </dt>
     *  Much like the method above, <code> newStop </code> is wrapped in a TripStopNode Object, albeit placed at the end of the list instead of before the cursor.  Appropriate links are created for this new TripStopNode in the list (including it becoming the tail)
     *  and count is incremented by one to reflect this insertion
     **/
    public void appendToTail(TripStop newStop) throws IllegalArgumentException {
        if (newStop == null) {
            throw new IllegalArgumentException();
        }
        TripStopNode newNode = new TripStopNode(newStop);
        if (tail == null) { //Meaning there are no nodes
            head = newNode;
            tail = head;
            cursor = head;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        count++;
    }
    
    /**
     * Returns and removes the current cursor, setting it to the previous TripStopNode and changing links as appropriate
     * @exception EndOfListException
     *  Thrown if the list is empty, meaning there is no TripStopNode in the list to remove
     * <dt> Postcondition </dt>:
     *  If there is only one TripStopNode, <code> cursor, head, and tail </code> are all set to null, making it an empty Itinerary again
     *  If <code> cursor </code> contains the same reference as <code> tail </code>, <code> tail </code> becomes the TripStopNode before, and the link to the node after that TripStopNode is removed
     *  If <code> cursor </code> is <code> head </code>, cursor will become the next TripStopNode in the list, and its <code> getPrev() </code> reference will be nullified
     *  Else, <code> cursor </code> becomes the reference to the next object in the list before the links are rearranged so that the <code> getNext() </code> of the new <code> cursor </code> becomes the TripStopNode after the removed node and that node's <code> getPrev() </code> links to the new cursor
     *  After execution of the above, count is decremented to account for the removal
     **/
    public TripStop removeCursor() throws EndOfListException {
        if (cursor == null)
            throw new EndOfListException();
        TripStop removed = cursor.getData();
        if (cursor == tail && cursor == head) { //If there is only one TripStopNode, this makes the list empty
            cursor = null;
            head = null;
            tail = null;
        }
        if (cursor == tail) {
            cursor.getPrev().setNext(null);
            cursor = cursor.getPrev();
            tail = cursor;
        }
        else if (cursor == head) {
            cursor.getNext().setPrev(null);
            cursor = cursor.getNext();
            head = cursor;
        }
        else {
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getNext();
        }
        count--;
        return removed;
    }
    /**
     * If the list is not empty and cursor is not yet set to the tail, sets <code> cursor </code> so it has the same reference as <code> tail </code>
     **/
    public void traverseToTail() {
        //Additional method
        if (cursor != tail && tail != null)
            cursor = tail;
    }
    /**
     * Creates a clone Itinerary Object, using clones of the TripStopNodes making up this Itinerary by invoking <code> clone() </code> in the TripStopNode class
     * @return 
     *  A clone of this Itinerary Object, set to the data type of Object.  To use as an Itinerary, must be typecasted
     **/
    @Override
    public Object clone() {
        Itinerary twin = new Itinerary();
        TripStopNode node = head;
        while (node != null) {
            twin.appendToTail((TripStop)node.getData().clone());
            node = node.getNext();
        }
        return twin;
    }
    
    /**
     * Creates a String representation of the Itinerary, invoking <code> toString() </code> in the TripStop Objects of each TripStopNode in this Itinerary
     * @return
     *  A summary of each TripStop, including <code> location, distance, and activity </code>
     **/
    @Override
    public String toString() {
        String s = "NOTE: '>' denotes the cursor\n" + " " + String.format("%-50s%-10s%-50s\n", "Location", "Distance", "Activity");
        TripStopNode tracker = head;
        while (tracker != null) {
            if (tracker == cursor) {
                s += ">" + tracker.getData().toString();
            }
            else {
                s += " " + tracker.getData().toString();
            }
            tracker = tracker.getNext();
        }
        s += "This trip has " + getStopsCount() + " stops, totaling " + getTotalDistance() + " miles."; //ask if you should put stops before and after the cursor
        return s;
    }
}
