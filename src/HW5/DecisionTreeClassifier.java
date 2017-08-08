/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW5;

import static HW5.TreeNavigator.buildTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * David S. Li 
 * 110328771 
 * Assignment #5 
 * CSE 214 R05 
 * Frank Migliorno 
 * Sun Lin
 *
 * @author dsli
 */
public class DecisionTreeClassifier {

    public static void main(String[] args) {
        System.out.println("Welcome to the DecisionTree Classifier.");
        try {
            runProgram();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured with reading the file. Please try again.");
        } catch (InvalidTreeFormatException e) {
            System.out.println(e.getErrorMessage());
        } finally {
            System.out.println("Thanks for using the DecisionTree Classifier!");
        }
    }

    public static void runProgram() throws FileNotFoundException, InvalidTreeFormatException {
        TreeNavigator t = new TreeNavigator();
        Scanner reader = new Scanner(System.in);
        boolean active = true;
        boolean editMenu = false;
        while (active) {
            if (editMenu) { //WORK ON ANY PORTIONS WITH TEXT, THEY MAY RELATE TO STRING ARRAYS NEEDED FOR THE NODES
                System.out.println("Edit Menu: ");
                System.out.println("E)Edit Keywords\n" + "C)Add Children \n"
                        + "D)Delete Children, and Make Leaf Ask user for new value for keyword(only one, no commas).\n"
                        + "N)Cursor to No Child\n"
                        + "Y)Cursor to Yes Child\n"
                        + "R)Cursor to Root\n"
                        + "P)Cursor to Parent \n"
                        + "M)Main Menu");
                System.out.println("Enter one of the above choices (NOTE: Only the first letter will be considered): ");
                String choice = reader.next();
                reader.nextLine();
                switch (choice.charAt(0)) {
                    case 'E':
                    case 'e':
                        System.out.println("Enter some keywords, separated by commas, to put into the cursor node (NOTE: DON'T INSERT SPACES AFTER THE COMMAS): ");
                        String text = reader.nextLine();
                        t.editCursor(text);
                        System.out.print("The keywords of the cursor were updated to: " + t.getCursor().keywordsToString());
                        break;
                    case 'c':
                    case 'C':
                        if (t.getCursor().getKeywords() == null) {
                            System.out.println("Cursor is null right now!  Must create a cursor before we can add children to it.");
                            System.out.print("Enter some text for the root node.  If there is more than one description, separate by commas (NOTE: DON'T INSERT SPACES AFTER THE COMMAS): ");
                            String[] rootText = reader.nextLine().toLowerCase().split(",");
                            t.getCursor().setKeywords(rootText);
                            System.out.println("Root created!  The keywords of the root are " + t.getCursor().keywordsToString());
                            System.out.print("Want to create the children for this node?  Y/N: ");
                            String yn = reader.nextLine();
                            if (yn.charAt(0) != 'y' && yn.charAt(0) != 'Y') {
                                break;
                            }
                        } else {
                            System.out.print("Enter new keywords to identify this TreeNode by, separated by commas (NOTE: DON'T INSERT SPACES AFTER THE COMMAS): ");
                            String newRtTxt = reader.nextLine().toLowerCase();
                            String[] newRootText = newRtTxt.split(",");
                            t.getCursor().setKeywords(newRootText);
                            System.out.println("The text for this node has now been changed to " + t.getCursor().keywordsToString());
                        }
                        //Can I get this to a String? And use editCursor?
                        System.out.print("Please enter text for the 'no' leaf: ");
                        String[] leftText = new String[]{reader.nextLine().toLowerCase()};
                        System.out.print("Please enter text for the 'yes' leaf: ");
                        String[] rightText = new String[]{reader.nextLine().toLowerCase()};
                        if (t.getCursor().getLeft() == null) {
                            t.getCursor().setLeft(new TreeNode());
                        }
                        if (t.getCursor().getRight() == null) {
                            t.getCursor().setRight(new TreeNode());
                        }
                        t.getCursor().getLeft().setKeywords(leftText);
                        t.getCursor().getRight().setKeywords(rightText);
                        System.out.println("Left child has been successfully set to " + t.getCursor().getLeft().keywordsToString());
                        System.out.println("Right child has been successfully set to " + t.getCursor().getRight().keywordsToString());
                        break;
                    case 'd':
                    case 'D':
                        t.getCursor().setLeft(null);
                        t.getCursor().setRight(null);
                        System.out.println("The left and right children no longer exist for this node.  Enter a single term for this new leaf: ");
                        String leafText = reader.nextLine();
                        t.getCursor().setKeywords(new String[]{leafText});
                        System.out.println("All set!  The new term for this leaf is " + t.getCursor().keywordsToString());
                        break;
                    case 'n':
                    case 'N':
                        if (t.getCursor() == null) {
                            System.out.println("The tree is empty.  First create a root and some children for it.");
                        } else if (t.getCursor().isLeaf() || t.getCursor().getLeft() == null) {
                            System.out.println("The cursor is a leaf.  Either go back to the root or to a parent or create leaves for this node.");
                        } else {
                            t.cursorLeft();
                            System.out.println("Cursor is now at the 'no' child: \n" + t.getCursor().toString()); //NULL PROVISIONS?
                        }
                        break;
                    case 'y':
                    case 'Y':
                        if (t.getCursor() == null) {
                            System.out.println("The tree is empty.  First create a root and some children for it.");
                        } else if (t.getCursor().isLeaf() || t.getCursor().getRight() == null) {
                            System.out.println("The cursor is a leaf.  Either go back to the root or to a parent or create leaves for this node.");
                        } else {
                            t.cursorRight();
                            System.out.println("Cursor is now at the 'yes' child: \n" + t.getCursor().toString()); //NULL PROVISIONS?
                        }
                        break;
                    case 'r':
                    case 'R':
                        if (t.getCursor() == null) {
                            System.out.println("The tree is empty.  Create a tree first.");
                        } else {
                            t.resetCursor();
                            System.out.println("Cursor reset!  The cursor is now at the root: \n" + t.getCursor().toString());
                        }
                        break;
                    case 'm':
                    case 'M':
                        editMenu = false;
                        break;
                    case 'p':
                    case 'P':
                        System.out.println(t.getParentOfCursor());
                    default:
                        System.out.println("Entry not understood.  Please enter a choice from the menu. ");
                }
            } else {
                System.out.println("Main Menu: ");

                System.out.println("I)Import a tree from a file\n"
                        + "E)Edit current tree\n"
                        + "C)Classify a Description\n"
                        + "P)Show decision path for a Description\n"
                        + "Q) Quit");
                System.out.println("Enter one of the above choices (NOTE: Only the first letter will be considered): ");
                String choice = reader.nextLine();
                switch (choice.charAt(0)) {
                    case 'I':
                    case 'i':
                        System.out.print("Enter a file path: ");
                        String filename = reader.next();
                        reader.nextLine();
                        File newFile = new File(filename);
                        Scanner fileReader = new Scanner(newFile);
                        String treeFile = "";
                        while (fileReader.hasNextLine()) {
                            treeFile += fileReader.nextLine() + "\n";
                        }
                        t = t.buildTree(treeFile);
                        System.out.println("A new tree has been successfully built, with its root being [" + t.getCursor().keywordsToString());
                        break;
                    case 'c':
                    case 'C':
                        if (t.getCursor().getKeywords() == null) {
                            System.out.println("There is currently no existing tree.  Import a tree or create your own.");
                        } else {
                            System.out.print("Enter some text: ");
                            String text = reader.nextLine();
                            System.out.println(t.classify(text));
                        }
                        break;
                    case 'e':
                    case 'E':
                        editMenu = true;
                        break;
                    case 'p':
                    case 'P':
                        if (t.getCursor().getKeywords() == null) {
                            System.out.println("Cursor is null.  Create or import a tree first.");
                        } else {
                            System.out.println("Decision path: " + t.getPath());
                        }
                        break;
                    case 'q':
                    case 'Q':
                        active = false;
                        break;
                    default:
                        System.out.println("Entry not understood.  Please enter a letter choice from the menu. ");
                }
            }
        }
    }
}
