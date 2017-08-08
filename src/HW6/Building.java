/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * David S. Li
 * 110328771
 * Assignment #6
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class Building implements Serializable {
    //Invariant: classrooms, a HashMap containing several Classroom objects labeled by their classroom numbers
    private HashMap<Integer, Classroom> classrooms;
    
    /**
     * Constructs an instance of a Building object
     * <dt> Postcondition: </dt>
     *      The Building Object is constructed, and the HashMap <code> classrooms </code> is initialized to a new, empty HashMap
     **/
    public Building() {
        classrooms = new HashMap();
    }
    
    /**
     * Puts a new Classroom Object into the classrooms HashMap
     * @param roomNumber
     *      An int number to serve as the key for the new Classroom
     * @param classroom
     *      A reference variable to the new Classroom being put into the HashMap
     * <dt> Precondition </dt>
     *      There cannot be an existing Classroom Object associated with the given <code> roomNumber </code> key
     * @exception IllegalArgumentException
     *      Thrown if a Classroom Object exists with the given <code> roomNumber </code> as its key, violating the above precondition
     * <dt> Postcondition </dt>
     *      The Classroom object, associated with <code> classroom </code> is put into the HashMap, associated with the key <code> roomNumber </code>
     **/
    public void addClassroom(int roomNumber, Classroom classroom) {
        if (classrooms.containsKey(roomNumber))
            throw new IllegalArgumentException();
        classrooms.put(roomNumber, classroom);
    }
    
    /**
     * Returns the Classroom object associated with the given roomNumber
     * @param roomNumber
     *      The int key whose Classroom value is to be returned
     * <dt> Precondition </dt>
     *      There MUST be a Classroom Object value associated with the <code> roomNumber </code> key
     * @exception IllegalArgumentException
     *      Thrown if there is no Classroom Object associated with <code> roomNumber </code>, violating the above precondition
     * @return 
     *      The Classroom object that we get from the HashMap associated with the <code> roomNumber </code> key
     **/
    public Classroom getClassroom(int roomNumber) {
        if (classrooms.containsKey(roomNumber))
            return (Classroom)classrooms.get(roomNumber);
        throw new IllegalArgumentException();
    }
    
    /**
     * Removes the Classroom object associated with the given roomNumber key
     * @param roomNumber
     *      An int key referencing the Classroom Object to be removed
     * <dt> Precondition </dt>
     *      The Classroom object with the <code> roomNumber </code> must exist in the HashMap in order for it to be removed from the HashMap
     * <dt> Postcondition </dt>
     *      If <code> roomNumber </code> is key to a Classroom Object in the HashMap, remove that Classroom.  Otherwise, do nothing.
     **/
    public void removeClassroom(int roomNumber) {
        if (classrooms.containsKey(roomNumber))
            classrooms.remove(roomNumber);
    }
    
    /**
     * Returns a String denoting a DETAILED Classroom list in this building, including properties of each classroom
     * @return
     *      A String s, denoting a detailed classroom list in this building, that lists the classrooms and their properties
     **/
    public String detailedClassroomList() {
        String s = "";
        Set classroomSet = classrooms.entrySet();
        for (HashMap.Entry<Integer, Classroom> classroom: classrooms.entrySet()) {
            int roomNumber = classroom.getKey();
            Classroom c = classroom.getValue();
            s +=  "Room " + roomNumber + "\n";
            s += c.toString();
        }
        return s;//not complete yet?
    }
    
    /**
     * Returns a list of each Classroom key (classroom numbers)
     * @return
     *      A String containing each Classroom number (lists the keys contained in the HashMap for all the classrooms)
     **/
    public String listRooms() {
        String s = "";
        for (Integer roomNumber: classrooms.keySet())
            s += roomNumber + " ";
        return s;
    }
    
    /**
     * Returns a summary of this Building, denoting how many rooms have whiteboards, chalkboards, etc. and the total amount of seats in all the classrooms
     * @return 
     *      A String s - it describes the total seats in this building, classrooms with whiteboards, classrooms with chalkboards, and AV Equipment available in the building's classrooms
     **/
    @Override
    public String toString() {
        int totalSeats = 0, totalRooms = classrooms.size(), totalWhiteboards = 0, totalChalkboards = 0;
        ArrayList<String> allAVEquip = new ArrayList();
        for (Classroom room: classrooms.values()) {
            totalSeats += room.getNumSeats();
            if (room.isHasWhiteboard())
                totalWhiteboards++;
            if (room.isHasChalkboard())
                totalChalkboards++;
            String[] avEquip = room.getAVEquipmentList();
            for (String equip: avEquip) { //Fix this so AV Equipment doesn't get printed twice
                if (!(allAVEquip.contains(equip))) {
                    allAVEquip.add(equip);
                }
            }
        }
        double percentWhiteboards = ((double)totalWhiteboards / totalRooms) * 100;
        double percentChalkboards = ((double)totalChalkboards / totalRooms) * 100;
        String allAVEquipment = "";
        for (String equip: allAVEquip) {
            allAVEquipment += equip + " ";
        }
        String s = "Total Seats: " + totalSeats + "\n";
        s += "Percent of rooms containing whiteboards: " + percentWhiteboards + "% \n";
        s += "Percent of rooms containing blackboards: " + percentChalkboards + "% \n";
        s += "List of AV Equipment that classrooms in this building have: " + allAVEquipment;
        return s;
    }
    
    /**
     * Returns a list of the Classrooms with whiteboards in this Building
     * @return
     *      A String s - has the list of Classrooms with whiteboards
     **/
    public String getRoomsWithWhiteboards() {
        String s = "";
        for (HashMap.Entry<Integer, Classroom> classroom: classrooms.entrySet()) {
            int roomNumber = classroom.getKey();
            Classroom cl = classroom.getValue();
            if (cl.isHasWhiteboard())
                s += roomNumber + " ";
        }
        return s;
    }
    
    /**
     * Returns a list of the Classrooms with chalkboards in this building
     * @return
     *      A String s - the string list of Classrooms with chalkboards
     **/
    public String getRoomsWithChalkboards() {
        String s = "";
        for (HashMap.Entry<Integer, Classroom> classroom: classrooms.entrySet()) {
            int roomNumber = classroom.getKey();
            Classroom cl = classroom.getValue();
            if (cl.isHasChalkboard())
                s += roomNumber + " ";
        }
        return s;
    }
    
    /**
     * Returns a String list of the rooms with the given type of AV Equipment
     * @param equip
     *      A String keyword denoting the type of AV Equipment desired
     * @return 
     *      A String s - a list of classrooms that contain the given <code> equip </code> parameter
     **/
    public String getRoomsWithEquip(String equip) {
        equip = equip.toLowerCase();
        String s = "";
        for (HashMap.Entry<Integer, Classroom> room: classrooms.entrySet()) {
            int roomNumber = room.getKey();
            Classroom c = room.getValue();
            if (c.containsAVEquip(equip))
                s += roomNumber + " ";
        }
        return s;
    }
}
