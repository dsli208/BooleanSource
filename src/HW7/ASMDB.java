/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW7;

import big.data.DataInstantiationException;
import java.util.Scanner;
import java.util.List;

/**
 * David S. Li
 * 110328771
 * Assignment #7
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class ASMDB {

    public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        MovieManager m = new MovieManager();
        boolean active = true;
        System.out.println("Welcome to A Smiley Movie Data Base, the happiest way to manage your DVD's");
        while (active) {
            System.out.println("I - Import Movie <Title>\n"
                    + "D - Delete Movie <Title>\n"
                    + "M - Sort Movies\n"
                    + "A - Sort Actors\n"
                    + /* +*/ "Q - Quit");
            String input = (reader.nextLine()).toUpperCase();
            switch (input) {
                case "I":
                    try {
                        System.out.print("Enter a movie title: ");
                        String movieTitle = reader.nextLine();
                        String prefix = "http://www.omdbapi.com/?t=";
                        String movieString = movieTitle.replace(' ', '+');
                        String postfix = "&y=&plot=short&r=xml";
                        String url = prefix + movieString + postfix;
                        Movie newMovie = new Movie(url);
                        List<Movie> movieList = m.getMovies();
                        movieList.add(newMovie);
                        List<Actor> thisMoviesActors = newMovie.getActors();
                        List<Actor> actorList = m.getActors();
                        for (int i = 0; i < thisMoviesActors.size(); i++) {
                            Actor a = thisMoviesActors.get(i);
                            boolean exists = false;
                            for (int j = 0; j < actorList.size(); j++) {
                                if (a.getName().equalsIgnoreCase(actorList.get(j).getName())) {
                                    exists = true;
                                    Actor replaceActor = actorList.get(j);
                                    thisMoviesActors.set(i, replaceActor);
                                    replaceActor.setCount(replaceActor.getCount() + 1);
                                    break;
                                }
                            }
                            if (!(exists)) {
                                actorList.add(a);
                                a.setCount(a.getCount() + 1);
                            }
                        }
                        System.out.println(movieTitle + " has been added to your library.");
                    } catch (DataInstantiationException e) {
                        System.out.println("This movie could not be found in the database.");
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Bad input.  Please try again.");
                    }
                    break;
                case "D":
                    System.out.print("Enter a movie to delete: ");
                    String movieToDelete = reader.nextLine();
                    List<Movie> movieList = m.getMovies();
                    Movie delete = null;
                    for (int i = 0; i < movieList.size(); i++) {
                        if ((movieList.get(i).getTitle()).equalsIgnoreCase(movieToDelete)) {
                            delete = movieList.get(i);
                            break;
                        }
                    }
                    if (delete != null) {
                        List<Actor> actorList = delete.getActors();
                        for (int i = 0; i < actorList.size(); i++) {
                            Actor a = actorList.get(i);
                            a.setCount(a.getCount() - 1);
                            if (a.getCount() == 0) {
                                m.getActors().remove(a);
                                
                            }
                        }
                        String deleteTitle = delete.getTitle();
                        movieList.remove(delete);
                        System.out.println(deleteTitle + " has been successfully deleted from the collection.");
                    } else {
                        System.out.println("That movie does not exist in the collection.  Try again.");
                    }
                    break;
                case "M":
                    sortMovies(m);
                    break;
                case "A":
                    sortActors(m);
                    break;
                case "Q":
                    active = false;
                    break;
                default:
                    System.out.println("Entry not understood.  Try again please.");
            }
        }
    }

    public static void sortMovies(MovieManager m) {
        System.out.println("Select an option below: \n" + "TA -By Title Ascending \n"
                + "TD - By Title Descending \n"
                + "YA - By Release Date Ascending\n"
                + "YD - By Release Date Descending ");
        String choice = reader.nextLine().toUpperCase();
        switch (choice) {
            case "TD":
                List<Movie> sortedMovies = m.getSortedMovies(new TitleComparator());
                System.out.printf("%-40s%-4s%-100s\n", "Title", "Year", "Actors");
                for (int i = 0; i < sortedMovies.size(); i++) {
                    System.out.println(sortedMovies.get(i).toString());
                }
                break;
            case "TA":
                List<Movie> sortMovies2 = m.getSortedMovies(new TitleComparator());
                System.out.printf("%-40s%-4s%-100s\n", "Title", "Year", "Actors");
                for (int i = sortMovies2.size() - 1; i >= 0; i--) {
                    System.out.println(sortMovies2.get(i).toString());
                }
                break;
            case "YA":
                List<Movie> sortYears1 = m.getSortedMovies(new YearComparator());
                System.out.printf("%-40s%-4s%-100s\n", "Title", "Year", "Actors");
                for (int i = sortYears1.size() - 1; i >= 0; i--) {
                    System.out.println(sortYears1.get(i).toString());
                }
                break;
            case "YD":
                List<Movie> sortYears2 = m.getSortedMovies(new YearComparator());
                System.out.printf("%-40s%-4s%-100s\n", "Title", "Year", "Actors");
                for (int i = 0; i < sortYears2.size(); i++) {
                    System.out.println(sortYears2.get(i).toString());
                }
                break;
            default:
                System.out.println("Entry not understood.");
        }
    }

    public static void sortActors(MovieManager m) {
        System.out.println("AA - Alphabetically Ascending\n"
                + "AD - Alphabetically Descending \n"
                + "NA - By Number of Movies They Are In Ascending\n"
                + "ND - By Number of Movies They Are In \n");
        String choice = reader.nextLine();
        switch (choice) {
            case "AD":
            List<Actor> sortActors1 = m.getSortedActors(new NameComparator());
            System.out.printf("%-30s%-4s\n", "Name", "Movie Count");
            for (int i = 0; i < sortActors1.size(); i++) {
                System.out.println(sortActors1.get(i).toString());
            }
            break;
            case "AA":
            List<Actor> sortActors2 = m.getSortedActors(new NameComparator());
            System.out.printf("%-30s%-4s\n", "Name", "Movie Count");
            for (int i = sortActors2.size() - 1; i >= 0; i--) {
                System.out.println(sortActors2.get(i).toString());
            }
            break;
            case "ND":
            List<Actor> sortCount1 = m.getSortedActors(new CountComparator());
            System.out.printf("%-30s%-4s\n", "Name", "Movie Count");
            for (int i = 0; i < sortCount1.size(); i++) {
                System.out.println(sortCount1.get(i).toString());
            }
            break;
            case "NA":
            List<Actor> sortCount2 = m.getSortedActors(new CountComparator());
            System.out.printf("%-30s%-4s\n", "Name", "Movie Count");
            for (int i = sortCount2.size() - 1; i >= 0; i--) {
                System.out.println(sortCount2.get(i).toString());
            }
            break;
            default: System.out.println("Entry not understood.");
        }
    }
}
