/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW7;
/**
 * David S. Li
 * 110328771
 * Assignment #7
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 **/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author dsli
 */
public class MovieManager {
    /**
     * Invariants:
     * movies, a List of Movie objects
     * actors, a List of Actor objects
     **/
    private List<Movie> movies;
    private List<Actor> actors;
    
    /**
     * Constructs an instance of a MovieManager object
     **/
    public MovieManager() {
        movies = new ArrayList<Movie>();
        actors = new ArrayList<Actor>();
    }
    
    /**
     * Returns the List of Movie objects
     * @return
     *      <code> movies </code>, the list of Movie objects
     **/
    public List<Movie> getMovies() {
        return movies;
    }
    
    /**
     * Returns the List of Actor objects
     * @return
     *      <code> actors </code>, the List of Actor objects
     **/
    public List<Actor> getActors() {
        return actors;
    }
    
    /**
     * Returns a SORTED List of Movie objects
     * @param comp
     *      The Comparator object used to compare and sort Movie objects accordingly
     * @return
     *      <code> cloneList </code> a List of Movie objects cloned from <code> movies </code> and subsequently sorted
     **/
    public List<Movie> getSortedMovies(Comparator comp) {
        List<Movie> cloneList = clone(movies);
        int movieListSize = cloneList.size();
        List<Movie> returnMovieList = new ArrayList<Movie>();
        while (cloneList.size() > 0) {
            Movie endMovie = cloneList.get(0);
            for (int i = 1; i < cloneList.size(); i++) {
                int compValue = comp.compare(endMovie, cloneList.get(i));
                if (compValue > 0)
                    endMovie = cloneList.get(i);
            }
            returnMovieList.add(endMovie);
            cloneList.remove(endMovie);
        }
        return returnMovieList;
    }
    
    /**
     * Similar to the above method, returns a SORTED list of Actor objects
     * @param comp
     *      Comparator class that compares Actor objects and sorts the accordingly
     * @return
     *      <code> cloneList </code>: similar to the method above, <code> actors </code> is cloned to create this list of Actor objects, which is subsequently rearranged and returned
     **/
    public List<Actor> getSortedActors(Comparator comp) {
        List<Actor> cloneList = clone(actors);
        int actorListSize = cloneList.size();
        List<Actor> returnActorList = new ArrayList<Actor>();
        while (cloneList.size() > 0) {
            Actor endActor = cloneList.get(0);
            for (int i = 1; i < cloneList.size(); i++) {
                int compValue = comp.compare(endActor, cloneList.get(i));
                if (compValue > 0)
                    endActor = cloneList.get(i);
            }
            returnActorList.add(endActor);
            cloneList.remove(endActor);
        }
        return returnActorList;
    }
    
    /**
     * Clones the List provided as the parameter and returns the clone
     * @param l
     *      The List to be cloned
     * @return
     *      <code> returnList </code>, referencing the clone of the original List <code> l </code>
     **/
    public List clone(List l) {
        List returnList;
        if (l.isEmpty())
            returnList = new ArrayList<>();
        else if (l.get(0) instanceof Movie)
            returnList = new ArrayList<Movie>();
        else if (l.get(0) instanceof Actor)
            returnList = new ArrayList<Actor>();
        else
            returnList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            returnList.add(l.get(i));
        }
        return returnList;
    }
}
