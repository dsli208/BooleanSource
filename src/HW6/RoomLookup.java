/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * David S. Li
 * 110328771
 * Assignment #6
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class RoomLookup {
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Welcome to the TLT Classroom Finder!");
        System.out.println("Checking for save file...");
        Campus c;
        //Is it the best thing to use try-catch?  These exceptions seem known so better to use if-else if you can
        try {
            c = loadCampus();
            System.out.println("File successfully loaded.");
        }
        catch (IOException e1) {
            System.out.println("No save file found.  Creating new Campus file.");
            c = new Campus();
        }
        catch (ClassNotFoundException e2) {
            System.out.println("No save file found.  Creating new Campus file.");
            c = new Campus();
        }
        catch (Exception e3) {
            System.out.println("No save file found.  Creating new Campus file.");
            c = new Campus();
        }
        boolean active = true;
        while (active) {
            System.out.println("Enter the letter for one of the following choices below: ");
            System.out.println("A - Add Building: with name\n"
                    + "D - Delete Building: by name\n"
                    + "E - Edit Building: by name\n"
                    //+ 
                    + "F - Find Room: Find by name, and print the details\n"
                    + "S - Search\n"
                    //+ " Options:\n"
                    //+ "W - Whiteboard: list all rooms containing a whiteboard in the scope\n"
                    //+ "B - Blackboard: list all rooms containing a blackboard in the scope\n"
                    //+ "A - AV Keyword: list all rooms containing a piece of av equipment in the scope\n"
                    + "C - List buildings on campus and their rooms.\n"
                    + "L - List summary of building: by name\n"
                    //+ "Total Seats\n"
                    //+ "Percent of rooms have whiteboard\n"
                    //+ "Percent of rooms have blackboard\n"
                    //+ "List of all AV Equipment\n"
                    + "Q - Quit\n");
            //+ "S - save\n"
            //+ "D - don't save");
            String choice = reader.next();
            if (choice.equals("")) {
                System.out.println("Empty entry not understood.");
            } else {
                char ch = choice.charAt(0);
                switch (ch) {
                    case 'A':
                    case 'a':
                        try {
                            System.out.println("Enter the name of the new building to be added (one word): ");
                            String buildingName = reader.next();
                            c.addBuilding(buildingName, new Building());
                            System.out.println(buildingName + " was successfully added to this campus.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("This building has already been added to the campus");
                        }
                        break;
                    case 'D':
                    case 'd':
                        try {
                            System.out.println("Enter the name of the building to be deleted: ");
                            String buildingToDelete = reader.next();
                            c.removeBuilding(buildingToDelete);
                            System.out.println("Successfully deleted!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("That building could not be found."); //StringOutOfBounds???
                        }
                        break;
                    case 'E':
                    case 'e':
                        try {
                            System.out.print("Enter a building to edit: ");
                            String buildingToEdit = reader.next();
                            Building b = c.getBuilding(buildingToEdit);
                            editBuilding(b);
                        } catch (IllegalArgumentException e) {
                            System.out.println("That building does not exist."); //StringOutOfBounds???
                        }
                        break;
                    case 'f':
                    case 'F': //Test
                        try {
                            System.out.print("Enter a room (building + room number): ");
                            reader.nextLine();
                            String roomToFind = reader.nextLine();
                            String[] buildingAndRoom = roomToFind.split("\\s");
                            if (buildingAndRoom.length != 2) {
                                throw new IllegalArgumentException();
                            }
                            String buildingName = buildingAndRoom[0];
                            int roomNumber = Integer.parseInt(buildingAndRoom[1]);
                            Building b = c.getBuilding(buildingName);
                            Classroom r = b.getClassroom(roomNumber);
                            System.out.println(r.toString());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Either bad input or room does not exist.  Please try again, remember: input must be building name (one word) + room NUMBER!");
                            /*Should i differentiate?*/
                        }
                        break;
                    case 's':
                    case 'S': //Test
                        boolean activeSearch = true;
                        String searchTerm;
                        char option;
                        System.out.println("Please enter one of the options below: ");
                        System.out.println("A - AV Equipment \nB - Blackboard\nW - Whiteboard\n");
                        searchTerm = reader.next();
                        while (searchTerm.equals("")) {
                            System.out.println("Entry not understood.  Try again please.");
                            System.out.println("Please enter one of the options below: ");
                            System.out.println("A - AV Equipment \nB/C - Blackboard/Chalkboard\nW - Whiteboard\n");
                            searchTerm = reader.next();
                        }
                        option = searchTerm.charAt(0);
                        System.out.println(searchCampus(option, c));
                    case 'c':
                    case 'C':
                        System.out.println(c.listBuildings()); //Maybe work on this a little
                        break;
                    case 'l':
                    case 'L':
                        try {
                            System.out.print("Enter a building: ");
                            String building = reader.next();
                            Building b = c.getBuilding(building);
                            System.out.println(b.toString());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erraneous input.  Building does not exist.");
                        }
                        break;
                    case 'Q':
                    case 'q': //Work on Serialization
                        active = false;
                        System.out.print("Do you want to save (Y/N)? ");
                        String save = reader.next();
                        char quit = save.charAt(0);
                        if (quit == 'Y' || quit == 'y') {
                            System.out.println("Saving file...");
                            try {
                                saveCampus(c);
                                System.out.println("File successfully saved.");
                            } catch (FileNotFoundException e) {
                                System.out.println("Fatal error occurred.");
                            } catch (IOException e1) {
                                System.out.println("Fatal error occurred.");
                            }
                        }
                        break;
                    default:
                        System.out.println("Entry not understood.");
                }
            }
        }
        System.out.println("Thank you for using the TLT ClassroomFinder!");
    }

    public static void editBuilding(Building building) {
        System.out.println("Launching editor for this building.");
        boolean active = true;
        while (active) {
            System.out.println("Enter one of the following choices below: ");
            System.out.println("A - Add Room\n"
                    + "D - Delete Room\n"
                    + "E - Edit room\n"
                    + "X - Exit building editor\n");
            String choice = reader.next();
            char ch = choice.charAt(0);
            switch (ch) {
                case 'A':
                case 'a':
                    try {
                        System.out.print("Please enter room number: ");
                        int roomToAdd = reader.nextInt();
                        Classroom cl = new Classroom();
                        building.addClassroom(roomToAdd, cl);
                        System.out.print("Enter the number of seats: ");
                        cl.setNumSeats(reader.nextInt());
                        reader.nextLine(); //prevents the next nextLine() from automatically activating
                        System.out.print("Please enter any AV equipment this room has: (separate by commas!) ");
                        String avEquip = reader.nextLine();
                        cl.setAVEquipmentList(avEquip.split(","));
                        System.out.print("Does this room have a whiteboard (Y/N)? ");
                        String containsWhiteboard = reader.next();
                        char whiteboardCh = containsWhiteboard.charAt(0);
                        if (whiteboardCh == 'Y' || whiteboardCh == 'y') {
                            cl.setHasWhiteboard(true);
                        }
                        System.out.print("Does this room have a chalkboard (Y/N)? ");
                        String containsChalkboard = reader.next();
                        char blackboardCh = containsChalkboard.charAt(0);
                        if (blackboardCh == 'Y' || blackboardCh == 'y') {
                            cl.setHasChalkboard(true);
                        }
                        System.out.println("Room " + roomToAdd + " has successfully been added to this building."); //possible to use reference of THIS building's name?
                    } catch (IllegalArgumentException e) {
                        System.out.println("There is already a classroom in this building associated with this number."); //Printing the contradiction number?
                        //reader.nextLine();
                    }
                    break;
                case 'D':
                case 'd':
                    try {
                        System.out.print("Enter the number of the room to be deleted: ");
                        int roomToDelete = reader.nextInt();
                        building.removeClassroom(roomToDelete);
                        System.out.println(roomToDelete + " successfully deleted.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("That room does not exist");
                        //reader.nextLine();
                    }
                    break;
                case 'e':
                case 'E':
                    try {
                        System.out.print("Enter the room number to edit: ");
                        int roomToEdit = reader.nextInt();
                        Classroom cl = building.getClassroom(roomToEdit);
                        System.out.println("The old number of seats in this room is " + cl.getNumSeats());
                        System.out.print("Do you wish to change the number of seats (Y/N)? ");
                        String changeSeats = reader.next();
                        char changeCh = changeSeats.charAt(0);
                        if (changeCh == 'Y' || changeCh == 'y') {
                            System.out.print("Enter the new number of seats: ");
                            int newNumSeats = reader.nextInt();
                            cl.setNumSeats(newNumSeats);
                            System.out.println("The new number of seats is now " + cl.getNumSeats());
                        }
                        String[] equipList = cl.getAVEquipmentList();
                        System.out.print("Old AV Equipment: ");
                        for (String s : equipList) {
                            System.out.print(s + " ");
                        }
                        System.out.println();
                        /*System.out.print("Replace with new AV Equipment(Y/N)? ");
                    String changeAVEquip = reader.next();
                    char changeAV = changeAVEquip.charAt(0);*/
                        reader.nextLine();//Test to make sure there is no error here!
                        System.out.println("If you would like to change the list of AV Equipment, enter the new list of AV Equipment here, separated by commas: ");
                        String newAVEquip = reader.nextLine();
                        if (!(newAVEquip.equals(""))) {
                            cl.setAVEquipmentList(newAVEquip.split(","));
                            String[] newEquip = cl.getAVEquipmentList();
                            System.out.print("AV Equipment List successfully changed!  The new list is: "); //"Modify" getAVEquipmentList() a little
                            for (String newS : newEquip) {
                                System.out.print(newS + " ");
                            }
                            System.out.println();
                        }
                        System.out.println("Does this room have a whiteboard ((Y/N), or press Enter to ignore)? ");
                        String containsWhiteboard = reader.nextLine();
                        if (!(containsWhiteboard.equals(""))) {
                            char whiteboardCh = containsWhiteboard.charAt(0);
                            if (whiteboardCh == 'Y' || whiteboardCh == 'y') {
                                cl.setHasWhiteboard(true);
                            } else if (whiteboardCh == 'n' || whiteboardCh == 'N') {
                                cl.setHasWhiteboard(false);
                            }
                        }
                        System.out.print("Does this room have a chalkboard ((Y/N), or press Enter to ignore)? ");
                        String containsChalkboard = reader.nextLine();
                        if (!(containsChalkboard.equals(""))) {
                            char blackboardCh = containsChalkboard.charAt(0);
                            if (blackboardCh == 'Y' || blackboardCh == 'y') {
                                cl.setHasChalkboard(true);
                            } else if (blackboardCh == 'N' || blackboardCh == 'n') {
                                cl.setHasChalkboard(false);
                            }
                        }
                        System.out.println("This room has been successfully edited!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("That room does not exist or you have entered an invalid input.  Try again.");
                        reader.nextLine();
                    }
                    break;
                case 'x':
                case 'X':
                    active = false;
                    System.out.println("Exiting building editor");
                    break;
                default:
                    System.out.println("Entry not understood.");
            }
        }
    }

    public static void saveCampus(Campus c) throws FileNotFoundException, IOException { //Can these exceptions be "handled" with if-else statements?
        FileOutputStream f = new FileOutputStream("c.obj");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(c);
        o.close();
    }

    public static Campus loadCampus() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream("c.obj");
        ObjectInputStream i = new ObjectInputStream(f);
        
        Campus loadC = (Campus)i.readObject();
        System.out.println("Loading file...");
        return loadC;
    }

    public static String searchCampus(char choice, Campus c) {

        switch (choice) {
            case 'a':
            case 'A':
                System.out.print("Enter a keyword: ");
                String avKeyword = reader.next();
                return searchCampus(avKeyword, c);
            case 'w':
            case 'W':
                return c.listRoomsWithWhiteboards();
            case 'b':
            case 'B':
            case 'c':
            case 'C':
                return c.listRoomsWithChalkboards();
            default:
                return "Erraneous input.  Please try again.";
        }
    }

    public static String searchCampus(String equipment, Campus c) {
        return c.listRoomsWithAVEquip(equipment);
    }
}
