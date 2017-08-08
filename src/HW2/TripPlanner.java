/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW2;

import java.util.Scanner;

/**
 * David S. Li
 * 110328771
 * Assignment #2
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class TripPlanner {

    public static void main(String[] args) {
        String placeholder;
        String location;
        String activity;
        int distance;
        boolean distanceNotEntered;
        Itinerary i1 = new Itinerary();
        Itinerary i2 = (Itinerary)i1.clone();
        Itinerary i = i1;
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to TripPlanner!");
        while (true) {
            System.out.println("Menu:\nF - Cursor forward\nB - Cursor backward\nI - Insert before cursor\nA - Append to tail\nD - Delete and move cursor forward\nH - Cursor to head\nT - Cursor to tail\nE - Edit cursor\nS - Switch itinerary\nO - Insert cursor from other itinerary after cursor from this itinerary\nR - Replace this itinerary with a copy of the other itinerary\nP - Print current itinerary, including summary\nC - Clear current itinerary\nQ - Quit");
            System.out.println("Enter a letter from one of the above choices (ONLY UPPERCASE LETTERS, AND ONLY THE FIRST LETTER ENTERED WILL BE CONSIDERED): ");
            String s = reader.next();
            char choice = s.charAt(0);
            switch (choice) {
                case 'F':
                    try {
                        i.cursorForward();
                    } catch (EndOfItineraryException e) {
                        System.out.println("You are already at the end of the itinerary.");
                    }
                    break;
                case 'B':
                    try {
                        i.cursorBackward();
                    } catch (EndOfItineraryException e) {
                        System.out.println("You are already at the beginning of the itinerary.");
                    }
                    break;
                case 'I':
                    System.out.print("Enter location: ");
                    placeholder = reader.nextLine();
                    location = reader.nextLine();
                    System.out.print("Enter an activity: ");
                    activity = reader.nextLine();
                    distanceNotEntered = true;
                    while (distanceNotEntered) {
                        try {
                            System.out.print("Enter a distance: ");
                            distance = reader.nextInt();
                            i.insertBeforeCursor(new TripStop(location, distance, activity));

                            distanceNotEntered = false;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Sorry, distance cannot be less than 0.  Please try again.");
                        }
                    }
                    System.out.println("The stop was successfully added!");
                    break;
                case 'A':
                    //
                    System.out.print("Enter location: ");
                    placeholder = reader.nextLine();
                    location = reader.nextLine();
                    System.out.print("Enter an activity: ");
                    activity = reader.nextLine();
                    distanceNotEntered = true;
                    while (distanceNotEntered) {
                        try {
                            System.out.print("Enter a distance: ");
                            distance = reader.nextInt();
                            i.appendToTail(new TripStop(location, distance, activity));

                            distanceNotEntered = false;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Sorry, distance cannot be less than 0.  Please try again.");
                        }
                    }
                    System.out.println("The stop was successfully added!");
                    break;
                case 'D':
                    try {
                        i.removeCursor();
                        System.out.println("Cursor successfully removed.");
                    } catch (EndOfListException e) {
                        System.out.println("Cursor is null.");
                    }
                    break;
                case 'H':
                    i.resetCursorToHead();
                    System.out.println("Cursor reset to head.");
                    break;
                case 'T':
                    //ask about creating another method in the Itinerary class that allows to easily traverse to the tail
                    i.traverseToTail();
                    System.out.println("Cursor is now set to the tail.");
                    break;
                case 'E':
                    System.out.print("Enter new location, or press Enter to keep your current one: ");
                    placeholder = reader.nextLine();
                    String newLocation = reader.nextLine();
                    if (!(newLocation.equals("")))
                        i.getCursorStop().setLocation(newLocation);
                    System.out.print("Enter new activity, or press Enter to keep your current one: ");
                    String newActivity = reader.nextLine();
                    if (!(newActivity.equals("")))
                        i.getCursorStop().setActivity(newActivity);
                    System.out.print("Enter a new distance, or enter a negative number to keep your current one: ");
                    int newDistance = reader.nextInt();
                    if (newDistance >= 0)
                        i.getCursorStop().setDistance(newDistance);
                    System.out.println("Cursor details successfully edited!");
                    break;
                case 'S':
                    i = (i == i1 ? i2 : i1);
                    System.out.println("Successfully switched to the other itinerary.");
                    break;
                case 'O':
                    Itinerary cloneItinerary = (i == i1 ? i2 : i1);
                    TripStop cloneStop = (TripStop)cloneItinerary.getCursorStop().clone();
                    i.insertBeforeCursor(cloneStop);
                    System.out.println("Cursor from the other Itinerary has successfully been added!");
                    break;
                case 'R': //Replaces this itinerary with a copy of the other itinerary
                    Itinerary itineraryToCopy = (Itinerary)(i == i1? i1 : i2);
                    Itinerary copyItinerary = (Itinerary)(i == i1? i2 : i1).clone();
                    i = copyItinerary;
                    //itineraryToCopy = copyItinerary;
                    if (i == i1) {
                        i1 = copyItinerary;
                    }
                    else
                        i2 = copyItinerary;
                    System.out.println("This itinerary has successfully been replaced with a clone of the other Itinerary!");
                    break;
                case 'P':
                    System.out.println(i.toString());
                    break;
                case 'C':
                    if (i == i1) {
                        i1 = new Itinerary();
                        i = i1;
                    }
                    else {
                        i2 = new Itinerary();
                        i = i2;
                    }
                    
                    System.out.println("Cleared!");
                    break;
                case 'Q':
                    System.out.println("Thank you for using TripPlanner!");
                    System.exit(0);
                default:
                    System.out.println("Your entry could not be understood.");
            }
        }

    }
}
