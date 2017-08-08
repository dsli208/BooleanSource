/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW6;

import java.io.Serializable;

/**
 * David S. Li
 * 110328771
 * Assignment #6
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class Classroom implements Serializable {

    /**
     * Invariants: hasWhiteboard - boolean value denoting that this Classroom
     * contains a Whiteboard hasChalkboard - boolean value denoting that this
     * Classroom contains a Chalkboard numSeats - an int denoting the number of
     * seats in this Classroom AVEquipmentList, a String[] (String array)
     * denoting the AV Equipment that this Classroom features
     *
     */
    private boolean hasWhiteboard;
    private boolean hasChalkboard;
    private int numSeats;
    private String[] AVEquipmentList;

    /**
     * Constructs an instance of the Classroom Object
     * <dt> Postcondition </dt>
     * By default, <code> hasWhiteboard </code> and <code> hasChalkboard </code>
     * are initialized to false, and <code> numSeats </code> is initialized to
     * 0. All values can be changed later.
     *
     */
    public Classroom() {
        hasWhiteboard = false;
        hasChalkboard = false;
        numSeats = 0;
    }

    /**
     * Gets whether this Classroom has a Whiteboard
     *
     * @return true if there is a whiteboard, false otherwise
     *
     */
    public boolean isHasWhiteboard() {
        return hasWhiteboard;
    }

    /**
     * Changes the value of the invariant <code> hasWhiteboard </code>
     *
     * @param hasWhiteboard Separate from the invariant above, a boolean
     * (true/false) value denoting if this classroom does have a whiteboard
     * <dt> Postcondition </dt>
     * The invariant </code> hasWhiteboard </code> is changed to reflect the
     * parameter
     *
     */
    public void setHasWhiteboard(boolean hasWhiteboard) {
        this.hasWhiteboard = hasWhiteboard;
    }

    /**
     * Gets whether this Classroom has a chalkboard
     *
     * @return true if there is a chalkboard, false otherwise
     *
     */
    public boolean isHasChalkboard() {
        return hasChalkboard;
    }

    /**
     * Changes the value of the invariant <code> hasChalkboard </code>
     *
     * @param hasChalkboard Separate from the invariant above, a boolean
     * (true/false) value denoting if this classroom does have a chalkboard
     * <dt> Postcondition </dt>
     * The invariant </code> hasChalkboard </code> is changed to reflect the
     * parameter
     *
     */
    public void setHasChalkboard(boolean hasChalkboard) {
        this.hasChalkboard = hasChalkboard;
    }

    /**
     * Returns the number of seats in this classroom
     *
     * @return <code> numSeats </code>, the invariant denoting number of seats
     * in this Classroom
     *
     */
    public int getNumSeats() {
        return numSeats;
    }

    /**
     * Changes the number of seats in this Classroom to the given parameter
     *
     * @param numSeats An int denoting the new number of seats in this Classroom
     * <dt> Precondition </dt>
     * <code> numSeats </code> cannot be less than 0 (We can't have a negative
     * number of seats in the Classroom!)
     * <dt> Postcondition </dt>
     * If <code> numSeats </code> is at least 0, then the invariant <code> numSeats
     * </code> will be changed to reflect the parameter
     */
    public void setNumSeats(int numSeats) {
        if (numSeats >= 0) {
            this.numSeats = numSeats;
        }
    }

    /**
     * Returns the <code> AVEquipmentList </code> in array form
     *
     * @return The String[] <code> AVEquipmentList </code> - Note in order to
     * get the Strings, we must print using a for each loop, this is done where
     * necessary in the other classes
     *
     */
    public String[] getAVEquipmentList() {
        return AVEquipmentList;
    }

    /**
     * Changes the <code> AVEquipmentList </code> based on the given parameter
     *
     * @param AVEquipmentList The new array of AV Equipment Strings to be
     * referenced by the invariant <code> AVEquipmentList </code>
     * <dt> Precondition </dt>
     * Cannot be an empty array
     * <dt> Postcondition </dt>
     * The invariant <code> AVEquipmentList </code> now references the given
     * parameter
     *
     */
    public void setAVEquipmentList(String[] AVEquipmentList) {
        if (AVEquipmentList != null) {
            for (String s : AVEquipmentList) {
                s = s.toLowerCase();
            }
            this.AVEquipmentList = AVEquipmentList;
        }
    }

    /**
     * Returns a String summary of this Classroom
     *
     * @return A String summary, denoting number of seats, whether it has a
     * whiteboard or chalkboard, and any AV Equipment that this Classroom has
     *
     */
    @Override
    public String toString() {
        String s = "";
        if (AVEquipmentList != null) {
            s += "AV Equipment: ";
            for (String equip : AVEquipmentList) {
                s += equip + " ";
            }
        }
        s += "\n" + "Number of seats: " + this.numSeats + "\n";
        s += "Has whiteboard? " + (this.isHasWhiteboard() ? "Yes" : "No") + "\n";
        s += "Has chalkboard? " + (this.isHasChalkboard() ? "Yes" : "No") + "\n";
        return s;
    }

    /**
     * Returns true or false, depending on whether this Classroom has the given equip parameter
     * @param equip
     *      A String keyword denoting the type of AV Equipment desired
     * @return
     *      true, if there is a String in the <code> AVEquipmentList </code> matching <code> equip </code>, false otherwise
     */
    public boolean containsAVEquip(String equip) {
        for (String s : AVEquipmentList) {
            if (equip.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
}
