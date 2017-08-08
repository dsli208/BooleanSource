/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW6;

import java.io.Serializable;
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
public class Campus implements Serializable {
    //Invariant: campusBuildings, a HashMap containing several Building objects keyed by Strings
    private HashMap<String, Building> campusBuildings;
    
    /**
     * Constructs an instance of a Campus
     * <dt> Postcondition </dt>
     *      <code> campusBuildings </code> is initialized to a new, empty HashMap object
     **/
    public Campus() {
        campusBuildings = new HashMap();
    }
    
    /**
     * Adds a Building object to the HashMap campusBuildings
     * @param buildingName
     *      A String to serve as the key to the Building object
     * @param building
     *      A reference to the Building object being put in the HashMap
     * <dt> Precondition </dt>
     *      There CANNOT be an instance of a Building put into the HashMap if an instance of Building is already occupying the given <code> buildingName </code>
     * <dt> Postcondition </dt>
     *      The Building object is put in the HashMap, its key set to <code> buildingName </code>
     * @exception IllegalArgumentException
     *      Thrown if the above precondition is violated (there is already a Building keyed to the given value of <code> buildingName </code>)
     **/
    public void addBuilding(String buildingName, Building building) {
        if (campusBuildings.containsKey(buildingName)) //buildingName or building
        {
            throw new IllegalArgumentException();
        }
        campusBuildings.put(buildingName, building);
    }
    
    /**
     * Returns a Building object with the specified buildingName
     * @param buildingName
     *      The key whose Building value is to be returned
     * <dt> Precondition: </dt>
     *      <code> buildingName </code> MUST reference an existing Building object in the HashMap
     * @exception IllegalArgumentException
     *      Thrown if a <code> buildingName </code> value is entered that does not key to a Building in the HashMap
     * @return
     *      The Building object that we get by associating it with the value of <code> buildingName </code> entered
     **/
    public Building getBuilding(String buildingName) {
        if (campusBuildings.containsKey(buildingName)) {
            return (Building) campusBuildings.get(buildingName);
        }
        throw new IllegalArgumentException();
    }
    
    /**
     * Removes the Building object with the specified buildingName
     * @param buildingName
     *      The key whose Building object is to be removed
     * <dt> Precondition: </dt>
     *      <code> buildingName </code> MUST reference an existing Building object in the HashMap
     * @exception IllegalArgumentException
     *      Thrown if a <code> buildingName </code> value is entered that does not key to a Building in the HashMap
     * <dt> Postcondition </dt>
     *      The Building associated with <code> buildingName </code> is removed from the HashMap
     **/
    public void removeBuilding(String buildingName) {
        if (campusBuildings.containsKey(buildingName)) {
            campusBuildings.remove(buildingName);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Returns a String summary of this Campus (lists buildings and their rooms and details in those rooms)
     * @return
     *      A String <code> s </code>, denoting buildings and their rooms
     **/
    @Override
    public String toString() {
        String s = "";
        Set buildingSet = campusBuildings.entrySet();
        for (HashMap.Entry<String, Building> building : campusBuildings.entrySet()) {
            String buildingName = building.getKey();
            Building b = building.getValue();
            s += buildingName + ": \n";
            s += b.toString() + "\n";
            s += "\n\n";
        }
        return s;
    }
    
    /**
     * Returns a String denoting the list of Buildings on this Campus
     * @return
     *      <code> s </code>, a String denoting the list of Buildings and rooms on this campus
     **/
    public String listBuildings() {
        String s = "";
        for (HashMap.Entry<String, Building> building : campusBuildings.entrySet()) {
            String buildingName = building.getKey();
            s += buildingName + ": ";
            String buildingRooms = building.getValue().listRooms();
            s += buildingRooms + "\n";
        }
        return s;
    }
    
    /**
     * Returns a String denoting a list of Classroom objects containing whiteboards
     * @return 
     *      <code> s </code>, a String denoting the list of Classrooms in each building with whiteboards, or denoting that there are no such classrooms
     **/
    public String listRoomsWithWhiteboards() {
        String s = "";
        for (HashMap.Entry<String, Building> building : campusBuildings.entrySet()) {
            String buildingName = building.getKey();
            Building b = building.getValue();
            if (!(b.getRoomsWithWhiteboards().equals(""))) {
                s += buildingName + ": " + b.getRoomsWithWhiteboards() + "\n";
            }
        }
        if (s.equals(""))
            return "There are no rooms that meet your criteria.";
        return s;
    }
    
    /**
     * Returns a String, listing buildings and their rooms with Chalkboards
     * @return
     *      A String denoting the list of Classrooms with chalkboards, or denoting that there are no such rooms
     **/
    public String listRoomsWithChalkboards() {
        String s = "";
        for (HashMap.Entry<String, Building> building : campusBuildings.entrySet()) {
            String buildingName = building.getKey();
            Building b = building.getValue();
            if (!(b.getRoomsWithChalkboards().equals(""))) {
                s += buildingName + ": " + b.getRoomsWithChalkboards() + "\n";
            }
        }
        if (s.equals(""))
            return "There are no rooms that meet your criteria.";
        return s;
    }
    
    /**
     * Returns a String, listing Buildings and their Classrooms with the given AV Equipment
     * @param equip
     *      A String keyword denoting the AV equipment desired
     * @return 
     *      A String listing Buildings and their Classrooms with the given <code> equip </code>, or denoting there are no such rooms
     **/
    public String listRoomsWithAVEquip(String equip) {
        String s = "";
        for (HashMap.Entry<String, Building> building : campusBuildings.entrySet()) {
            String buildingName = building.getKey();
            Building b = building.getValue();
            if (!(b.getRoomsWithEquip(equip).equals(""))) {
                s += buildingName + ": " + b.getRoomsWithEquip(equip) + "\n";
            }
        }
        if (s.equals(""))
            return "There are no rooms that meet your criteria.";
        return s;
    }
}
