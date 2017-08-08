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
 *
 * @author dsli
 */
public class TripStop {
    /**
     * Invariants:
     * location, a String describing the location of this stop
     * distance, an int telling how far away from the origin this stop is
     * activity, a String describing any activities to be performed there
     **/
    private String location;
    private int distance;
    private String activity;
    /**
     * Returns a "blank" instance of a TripStop
     * <code> location and activity </code> are initialized to empty Strings, while <code> distance </code> is initialized to 0
     **/
    public TripStop() {
        this.location = "";
        this.distance = 0;
        this.activity = "";
    }
    /**
     * Returns a customized instance of a TripStop object
     * @param location
     *  String describing where this TripStop is
     * @param distance
     *  <code> int </code> telling how far away from the origin
     * @param activity
     * String describing activities to be performed at this TripStop
     * 
     * <dt> Precondition: </dt>
     *  <code> distance </code> must be greater than or equal to 0
     * @exception IllegalArgumentException
     *  If the above precondition is not met, the above exception is thrown
     **/
    public TripStop(String location, int distance, String activity) throws IllegalArgumentException {
        if (distance < 0)
            throw new IllegalArgumentException();
        this.location = location;
        this.distance = distance;
        this.activity = activity;
    }
    /**Returns the location of the TripStop
     * @return
     *      <code> location </code> the String describing the location
     **/
    public String getLocation() {
        return location;
    }
    /**
     * Returns the distance from the origin
     * @return 
     *  <code> distance </code>, the int describing distance from the origin of the trip
     **/
    public int getDistance() {
        return distance;
    }
    /**
     * Returns the String representing any activities performed at the location
     * @return
     *  <code> activity </code>, the String describing activities happening at this location
     **/
    public String activity() {
        return activity;
    }
    /**
     * Changes location based on the String parameter
     * @param location
     *      String describing the new location of the TripStop
     * <dt> Postcondition </dt>
     *      <code> location </code> for this TripStop is changed to match the parameter
     **/
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Changes distance to a new value
     * @param distance
     *      The new value of <code> distance </code>
     * <dt> Postcondition </dt>
     *      <code> distance </code> reflects the value of the above parameter
     **/
    public void setDistance(int distance) throws IllegalArgumentException {
        if (distance < 0)
            throw new IllegalArgumentException();
        this.distance = distance;
    }
    /**
     * Changes the activity String
     * @param activity
     *      The new activities to be performed at the TripStop
     * <dt> Postcondition </dt>
     *      <code> activity </code> is changed to reflect the above parameter
     **/
    public void setActivity(String activity) {
        this.activity = activity;
    }
    /**
     * Creates a new TripStop object, using the same location, distance, and activity
     * @return 
     *      A TripStop Object created using the new operator and where <code> location, distance, activity </code> are copied in, but this is a new object created using the new operator with a difference reference
     **/
    @Override
    public Object clone() {
        return (TripStop)new TripStop(location, distance, activity);
    }
    /**
     * Creates a formatted String with <code> location, distance, activity </code>
     * @return
     *      Formatted string <code> s </code>
     **/
    @Override
    public String toString() {
        String s = String.format("%-51s%-10d%-50s\n", location, distance, activity);
        return s;
    }
}
