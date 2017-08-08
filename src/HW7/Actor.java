/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW7;

/**
 * David S. Li 110328771 Assignment #7 CSE 214 R05 Frank Migliorno Sun Lin
 *
 * @author dsli
 */
public class Actor {

    /**
     * Invariants: name, String representing the actors name count, int denoting
     * how many films are in the collection that this actor is in
     *
     */
    private String name;
    private int count;

    /**
     * Returns the name of the actor
     *
     * @return <code> name </code>, the name of this Actor
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Changes/sets the name of the actor
     *
     * @param name The (new) name of this Actor
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the count of movies in the collection that this actor is in
     *
     * @return <code> count </code>, the number of films that this Actor is in
     * (in the user's collection)
     *
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of actors that this movie is in
     *
     * @param count The new value of <code> count </code> for this Actor
     *
     * <dt> Precondition </dt>
     * <code> count </code> CANNOT be a negative number, or this method will
     * simply not run (can be zero, resulting in an Actor's removal from the
     * Actor List)
     *
     * <dt> Postcondition </dt>
     * <code> count </code> is changed to reflect the passed parameter
     */
    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }

    /**
     * Constructs an instance of an Actor
     *
     * @param name The name of the newly created Actor object
     * <dt> Precondition </dt>
     * <code> name </code> CANNOT be an empty String
     * @exception IllegalArgumentException Thrown if <code> name </code> is an
     * empty String
     * <dt> Postcondition </dt>
     * The new Actor object is created, <code> name </code> is initialized to
     * the parameter and <code> count </code> is initialized to 0
     *
     */
    public Actor(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.count = 0;
    }

    /**
     * Returns a String summary of this Actor
     *
     * @return A formatted String containing the Actor's name and how many
     * movies he stars in (in the user's collection)
     *
     */
    @Override
    public String toString() {
        String s = String.format("%-30s%-4d", this.name, this.count);
        return s;
    }
}
