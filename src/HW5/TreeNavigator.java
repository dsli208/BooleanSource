/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW5;

/**
 * David S. Li 110328771 Assignment #5 CSE 214 R05 Frank Migliorno Sun Lin
 *
 * @author dsli
 */
public class TreeNavigator {

    /**
     * Invariants: root, the TreeNode at the top of the tree 
     * cursor, a reference to the TreeNode being accessed 
     * cursorPath, a String denoting the path from the root to the cursor
     *
     */
    private TreeNode root;
    private TreeNode cursor;
    private String cursorPath;

    /**
     * Constructs an instance of TreeNavigator
     * <dt> Postcondition </dt>
     * <code> root </code> is initialized to a new TreeNode (initialized to its
     * default, no keywords yet), <code> cursor </code> is initialized to that
     * root and <code> cursorPath </code> is "0"
     *
     */
    public TreeNavigator() {
        root = new TreeNode();
        cursor = root;
        cursorPath = "0";
    }

    /**
     * Returns a properly formatted TreeNavigator, given a String obtained from
     * reading a properly formatted text file
     *
     * @param treeFile Denotes a String of the read in from a properly formatted
     * text file, given in the DecisionTreeClassifier driver program
     * @exception InvalidTreeFormatException Thrown if the file is incorrectly
     * formatted in some way
     * @return A instance of a TreeNavigator with nodes laid out given the
     * properly formatted <code> treeFile </code>
     *
     */
    public static TreeNavigator buildTree(String treeFile) throws InvalidTreeFormatException {
        TreeNavigator newTree = new TreeNavigator();
        newTree.root = new TreeNode();
        String[] split = treeFile.split("\n");
        for (String s : split) {
            String[] splitString = s.split(";"); //Doesn't split properly.  String[]'s are too large, as reported by program
            if (splitString.length != 3) {
                throw new InvalidTreeFormatException("Invalid formatting.  File should be formatted as follows:\n0's and 1's, separated by hyphens; keywords; and leaf/nonleaf\nAll of the above should have a semicolon in between.");
            }
            int i = 1;
            splitString[0] = splitString[0].replaceAll("-", "");
            String traversalString = splitString[0];
            System.out.println(splitString[0]);
            String treeNodeContents = splitString[1];
            String[] contentsArray = treeNodeContents.split(",");
            String booleanValue = splitString[2];
            //Traversing through the first part of the line

            //Special case for creating the first node
            if (traversalString.length() == 1 && traversalString.charAt(0) == '0') {
                newTree.root.setKeywords(contentsArray);
                if (booleanValue.equalsIgnoreCase("nonleaf")) {
                    newTree.root.setLeft(new TreeNode());
                    newTree.root.setRight(new TreeNode());
                }
            } //Otherwise, start at the root, and traverse based on the 0's and 1's 
            else {
                TreeNode traverseCursor = newTree.root;
                TreeNode parentNode;
                TreeNode nextNode;
                while (i < traversalString.length()) {

                    //
                    if (traversalString.charAt(i) == '1') {
                        if (traverseCursor.getRight() == null) {
                            traverseCursor.setRight(new TreeNode());
                        }
                        traverseCursor = traverseCursor.getRight();
                    } else if (traversalString.charAt(i) == '0') {
                        if (traverseCursor.getLeft() == null) {
                            traverseCursor.setLeft(new TreeNode());
                        }
                        traverseCursor = traverseCursor.getLeft();
                    } else {
                        throw new InvalidTreeFormatException("The tree traversal String can only have 0's and 1's, separated by hyphens!");
                    }
                    i++;
                    /*else if (s.charAt(i + 1) == ';' && (s.charAt(i) == '0' || s.charAt(i) == '1')) {
                    if (cursor == null) {
                        newTree.root = new TreeNode();
                    }

                }*/
                }
                //If this TreeNode is a nonleaf, we will create the left and right nodes so it will be easier to traverse to them the next time
                if (booleanValue.equalsIgnoreCase("nonleaf")) {
                    traverseCursor.setLeft(new TreeNode());
                    traverseCursor.setRight(new TreeNode());
                }
                traverseCursor.setKeywords(contentsArray);
            }

        }
        newTree.cursor = newTree.root;
        return newTree;
    }

    /**
     * Finds a node given a String of text to classify (subsequently, cursor
     * becomes that node) and returns a String pertaining to that node
     *
     * @param text A String used as the basis for classification
     * @return A String of the leaf that was reached as a result of classifying <code> text
     * </code>.
     * <dt> Postcondition </dt>
     * <code> cursor </code> is now at the classified node pertaining to <code> text
     * </code>
     *
     */
    public String classify(String text) {
        text = text.toLowerCase();
        resetCursor();
        TreeNode prevNode = null;
        while (!cursor.isLeaf()) {
            String[] thisKeywords = cursor.getKeywords();
            boolean containsAKeyword = false;
            for (String s : thisKeywords) {
                String[] wordsInASentence = s.split(" ");
                for (String word : wordsInASentence) {
                    word = word.toLowerCase();
                    if (text.contains(word)) { //Never becomes true
                        containsAKeyword = true;
                        break;
                    }
                }
            }
            prevNode = cursor;
            if (containsAKeyword) {
                cursorRight();
            } else {
                cursorLeft();
            }
        }
        String classification = "";
        for (String s : cursor.getKeywords()) {
            classification += s;
        }
        return classification;
    }

    /**
     * Returns a String denoting the path to get to the current location of <code> cursor
     * </code>
     *
     * @return A String <code> path </code> that denotes the path to get to
     * cursor using <code> cursorPath </code>. Note since we are using <code> cursorPath
     * </code> we CANNOT use <code> getRight(), getLeft() </code> since those
     * methods would modify <code> cursorPath </code> (See below)
     *
     */
    public String getPath() {
        //First, make an empty String
        String path = "";
        //Start traversal at the root
        TreeNode traverseNode = root;
        //For each character in the String, starting with the second, since that is where we start going through the branches
        for (int i = 1; i <= cursorPath.length(); i++) {

            //Getting information from the root/current node before traversing each time
            String[] traverseKeys = traverseNode.getKeywords();
            //Creating a additive String for this node
            String additive = "";
            //Getting the words to add to additive
            for (String key : traverseKeys) {
                additive += key + ", ";
            }
            //If we are at the last node, we are at the decision leaf
            if (i == cursorPath.length()) {
                path += "DECISION: ";
                path += additive + '\n';
            } //Otherwise
            else {
                //No leaf?
                if (cursorPath.charAt(i) == '0') {
                    path += "NOT " + additive;
                    traverseNode = traverseNode.getLeft();
                } //Or yes leaf?
                else { //(cursorPath.charAt(i) == '1')
                    path += additive;
                    traverseNode = traverseNode.getRight();
                }
            }
        }

        return path;
    }

    /**
     * Sets <code> cursor </code> to the <code> root </code> position, and
     * changes <code> cursorPath </code> accordingly
     * <dt> Postcondition </dt>
     * <code> cursor </code> is now back at <code> root </code>, <code> cursorPath
     * </code> is reset to "0" (denoting the first node)
     *
     */
    public void resetCursor() {
        cursor = root;
        cursorPath = "0";
    }

    /**
     * Sets the cursor to its left child (if not null)
     * <dt> Postcondition </dt>
     * <code> cursor </code> now has the reference of its left child, and <code> cursorPath
     * </code> has a "0" for "no" appended to it
     */
    public void cursorLeft() {
        if (cursor.getLeft() != null) {
            cursor = cursor.getLeft();
            cursorPath += "0";
        }
    }

    /**
     * Sets the cursor to its right child (if not null)
     * <dt> Postcondition </dt>
     * <code> cursor </code> now has the reference of its right child, and <code> cursorPath
     * </code> has a "1" for "yes" appended to it
     */
    public void cursorRight() {
        if (cursor.getRight() != null) {
            cursor = cursor.getRight();
            cursorPath += "1";
        }
    }

    /**
     * Returns the cursor object
     *
     * @return <code> cursor </code>
     *
     */
    public TreeNode getCursor() {
        if (cursor == null) //meaning we have an empty tree
        {
            return null;
        }
        return cursor;
    }

    /**
     * Edits the keywords pertaining to the current cursor
     *
     * @param text The new set of keywords (separated by commas) to pertain to
     * this TreeNode
     * <dt> Postcondition </dt>
     * <code> text </code> is split by commas to a new String[] <code> keywords
     * </code>, which is set as the new array of keywords for this TreeNode
     *
     */
    public void editCursor(String text) {
        String[] keywords = text.split(",");
        if (cursor == null) { //Empty tree
            root = new TreeNode();
            cursor = root;
        }
        cursor.setKeywords(keywords);
    }

    /**
     * Returns a String denoting the parent of the cursor, and sets the cursor
     * to its parent
     *
     * @exception InvalidTreeFormatException If at any point while traversing <code> cursorPath
     * </code> to get to the parent we encounter a char other than '0' or '1',
     * thrown
     * @return A String describing the parent of the cursor in its initial
     * location (if it isn't root), obtained by traversing the substring of <code> cursorPath
     * </code> to its second to last character (denoting the parent)
     * <dt> Postcondition </dt>
     * <code> cursor </code> now has the reference of its parent, unless it was
     * initially at the root
     *
     */
    public String getParentOfCursor() throws InvalidTreeFormatException {
        if (cursor == root) {
            return "We are at the root, it does not have a parent!";
        }
        cursor = root;
        for (int i = 1; i < cursorPath.length() - 1; i++) {
            if (cursorPath.charAt(i) == '0') {
                cursor = cursor.getLeft();
            } else if (cursorPath.charAt(i) == '1') {
                cursor = cursor.getRight();
            } else {
                throw new InvalidTreeFormatException("Error with cursorPath String, can only have 0's and 1's");
            }
        }
        cursorPath = cursorPath.substring(0, cursorPath.length() - 1);
        return "The cursor is now at its parent, with keywords " + cursor.keywordsToString();
    }
}
