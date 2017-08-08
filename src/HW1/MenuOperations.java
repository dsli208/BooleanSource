/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *David Li
 * 110328771
 * Assignment #1
 * CSE 214 R05
 * Frank Migliorno
 * 
 * 
 * @author dsli
 */
public class MenuOperations {

    public static void main(String[] args) throws CloneNotSupportedException, FullListException {
        Menu m = new Menu(); //menu that we are basing off of
        Menu orders = (Menu)m.clone(); //Menu object that we are using for orders
        //System.out.println(m.equals((Menu)orders));
        int amtOfItemsOrdered = 0;
        Scanner reader = new Scanner(System.in);
        boolean active = true;
        do {
            System.out.println("Choose a command below by entering the associated character (NOTE: only the FIRST character entered will be considered) : \nAdd Item - A");
            System.out.println("Get Item - G");
            System.out.println("Remove Item - R");
            System.out.println("Print All Items - P");
            System.out.println("Get Size - S");
            System.out.println("Update Description - D");
            System.out.println("Update Price - C");
            System.out.println("Add to order - O");
            System.out.println("Remove from order - I");
            System.out.println("View order - V");
            System.out.println("Quit - Q");
            String input = reader.next();
            String choice = input.substring(0, 1);
            String placeholder = reader.nextLine();
            if (choice.equalsIgnoreCase("A")) {
                System.out.print("Enter your item name: ");
                String name = reader.nextLine();
                System.out.print("Enter a description of your item: ");
                String desc = reader.nextLine();
                
                try {
                System.out.print("Enter your price: $");
                double price = reader.nextDouble();
                System.out.print("Enter the position where you would like to place it on the menu (1 - 50): ");
                m.addItem(new MenuItem(name, desc, price), reader.nextInt());
                System.out.println(name + " was successfully added to the menu");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Invalid argument.  Remember, your position must be GREATER than 0 and less than one more than the menu size.");
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input.  Try again.");
                }
            } else if (choice.equalsIgnoreCase("G")) {
                System.out.print("What item would you like to view?  Enter the position number: ");
                
                try {
                    MenuItem itemReceived = m.getItem(reader.nextInt());
                    System.out.printf("%-3s%-15s%-30s%-15s\n", "#", "Name", "Description", "Price");
                    System.out.println(itemReceived.toString());
                }
                catch (NullPointerException e) {
                    System.out.println("Sorry, that item could not be found!");
                }
            } else if (choice.equalsIgnoreCase("R")) {
                System.out.print("What item would you like to remove from the menu?  Enter the position number: ");
                m.removeItem(reader.nextInt());
            } else if (choice.equalsIgnoreCase("P")) {
                m.printAllItems();
            } else if (choice.equalsIgnoreCase("S")) {
                System.out.println(m.size());
            } else if (choice.equalsIgnoreCase("D")) {
                System.out.print("Enter the name of the item whose description you would like to update: ");
                String name = reader.nextLine();
                try {
                    MenuItem m1 = m.getItemByName(name);
                    System.out.print("Enter the new description: ");
                    m1.setDescription(reader.nextLine());
                } catch (NullPointerException e) {
                    System.out.println("That menu item could not be found.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Entry not understood.");
                }
            } else if (choice.equalsIgnoreCase("C")) {
                System.out.print("Enter the name of the item you would like to rename: ");
                String item = reader.nextLine();
                try {
                    MenuItem itemToRename = m.getItemByName(item);
                    System.out.print("Enter the new price of the item: $");
                    itemToRename.setPrice(reader.nextDouble());
                    System.out.println("Congratulations!  The price of " + itemToRename.getName() + " is " + itemToRename.getPrice());
                } catch (NullPointerException e) {
                    System.out.println("That item could not be found.");
                }
                catch (InputMismatchException e) {
                    System.out.println("Input mismatch.  Enter a number.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Entry not understood.");
                }
            } else if (choice.equalsIgnoreCase("O")) { //Since you are using an instance of the Menu object, write a provision for what happens if you try to exceed 50 items
                System.out.print("Enter the position of the item you would like to add to your order: ");
                int pos = reader.nextInt();
                try {
                orders.addItem(m.getItem(pos), ++amtOfItemsOrdered);
                System.out.println(m.getItem(pos).getName() + " has been successfully added to your order.");
                }
                catch (NullPointerException e) {
                    System.out.println("That item does not exist");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Entry not understood");
                }
            } else if (choice.equalsIgnoreCase("I")) {
                System.out.print("Enter the position of the item that you would like to remove from your order: ");
                int pos = reader.nextInt();
                try {
                    System.out.println("Removing " + orders.getItem(pos).getName() + " from the menu.");
                    orders.removeItem(pos);
                    amtOfItemsOrdered--;
                    System.out.println("Success!");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Entry not understood.");
                }
            } else if (choice.equalsIgnoreCase("V")) {
                System.out.println("YOUR ORDER: ");
                orders.printAllItems();
            } else if (choice.equalsIgnoreCase("Q")) {
                active = false;
            } else {
                System.out.println("No such operation.  Please try again.");
            }
        } while (active);
        System.out.println("Thank you!  Have a nice day.");
    }
}
