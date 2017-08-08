/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW7;

import java.util.ArrayList;
import java.util.List;
import big.data.DataSource;

/**
 * David S. Li
 * 110328771
 * Assignment #7
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class Movie {

    /**
     * Invariants: title, String that denotes the Movie's title year, int that
     * denotes when the movie was produced actors, a List of Actor objects
     * representing Actors in this movie actorString, a String listing all the
     * Actor's names for this movie
     *
     */
    private String title;
    private int year;
    private List<Actor> actors;
    private String actorString;

    /**
     * Returns <code> title </code>
     *
     * @return <code> title </code>, denoting the title of this movie
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this movie to the String parameter passed
     *
     * @param title The new title for this Movie object
     * <dt> Postcondition </dt>
     * <code> title </code> is changed to reflect the passed String parameter <code> title
     * </code>
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the year the movie was made
     *
     * @return <code> year </code>, int denoting when the movie was made
     *
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year to match the passed parameter
     *
     * @param year The updated year in which the movie was made
     * <dt> Postcondition </dt>
     * <code> year </code> is changed from its initial value to match the passed
     * parameter
     *
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a list of Actor objects pertaining to this particular Movie
     *
     * @return <code> actors </code>, the List of Actor objects, representing
     * actors starring in this movie
     *
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     * Taking in a new List of Actors, sets that new List so that the actors
     * variable references that list
     *
     * @param actors The new List of Actor objects for this Movie
     * <dt> Precondition </dt>
     * actors CANNOT be an empty List.
     * <dt> Postcondition </dt>
     * actors will now reference the new List, passed as the parameter
     *
     */
    public void setActors(List<Actor> actors) {
        if (actors.size() != 0) {
            this.actors = actors;
        }
    }

    /**
     * Constructs an instance of a Movie object (given a String name and int
     * year)
     *
     * @param title String representing the title of the Movie
     * @param year int representing the year the movie was made
     * <dt> Precondition </dt>
     * <code> year </code> cannot be negative, and <code> title </code> cannot
     * be an empty String
     * @exception IllegalArgumentException Thrown if any of the preconditions
     * above are violated (negative year, or title is an empty String)
     * <dt> Postcondition </dt>
     * <code> title </code> and <code> year </code> are initialized in term sof
     * their respective parameters.  <code> actors </code> is initialized as an
     * empty List
     *
     */
    public Movie(String title, int year) {
        if (title.equals("") || year < 0) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.year = year;
        this.actors = new ArrayList<Actor>();
    }

    /**
     * Constructs an instance of a Movie object (given a String url)
     *
     * @param url The URL Address for this movie on the OMDb database, passed as
     * a String
     * @exception DataInstantiationException Thrown if the passed url cannot
     * load a movie successfully
     * @exception IllegalArgumentException Thrown if <code> url </code> is an
     * empty String
     * <dt> Postcondition </dt>
     * The title and year are extracted from the database and <code> title
     * </code> and <code> year </code> are initialized to those respective
     * values.  <code> actors </code> is initialized to a new ArrayList, and a
     * String <code> actorString </code> is initialized when extracting actor
     * information from the database. Upon splitting this String to a String[]
     * (to remove commas from the String), Actor objects are constructed from
     * each of these Strings and added to <code> actors </code>
     *
     *
     */
    public Movie(String url) {
        if (url.equals("")) {
            throw new IllegalArgumentException();
        }
        DataSource d = DataSource.connectXML(url);
        d.load();
        this.title = d.fetchString("movie/title");
        this.year = d.fetchInt("movie/year");
        this.actorString = d.fetchString("movie/actors");
        this.actors = new ArrayList<Actor>();
        String[] actorList = (actorString).split(", ");
        for (String s : actorList) {
            actors.add(new Actor(s));
        }
    }

    /**
     * Returns a String summary of this Movie object, appropriate for when the
     * Movies are being listed as they are being sorted
     *
     * @return Formatted String containing the <code> title </code>, <code> year
     * </code>, and <code> actorString </code>, the String summary of Actors in
     * this Movie
     *
     */
    @Override
    public String toString() {
        return String.format("%-40s%-4d%-100s", this.title, this.year, this.actorString);
    }
}
