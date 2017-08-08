/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW1;

/**
 * David Li
 * 110328771
 * Assignment #1
 * CSE 214 R05
 * Frank Migliorno
 * 
 *
 * @author dsli
 */
//USE CLONES WHEN ADDING ITEMS?
public class Menu implements Cloneable {
    /*Instance variables:
    - MAX_CAPACITY is the most MenuItems the below MenuItem[] can have (50)
    - size is a counter variable, displaying the amount of MenuItem objects in the array items so far
    - items is a MenuItem[] initialized to size MAX_CAPACITY*/
    final int MAX_CAPACITY = 50;
    private int size;
    private MenuItem[] items;
    
    /**
     * Returns an instance of <code> Menu </code>
     * Postcondition: The MenuItem[] items is initialized to an array of capacity 50, and size is initialized to 0
    **/
    public Menu() {
        items = new MenuItem[MAX_CAPACITY];
        size = 0;
    }

   /**Returns a Menu Object clone of this Menu Object
    *@exception CloneNotSupportedException
    *   Indicates this Object does not implement the Cloneable interface
    **/
    @Override
    public Object clone() throws CloneNotSupportedException {
        Menu m = new Menu();
        //how to copy the objects over exactly?
        for (int i = 0; i < size; i++) {
            m.items[i] = (MenuItem)items[i].clone();
        }
        return (Menu) m;
    }
    
    /*Indicates whether the Object o is equal to this Menu object
    *@param Object o
        The Object to be compared with this Menu object
    @return
        True if o is an instance of a Menu Object,  the sizes are equal, and for each position the MenuItem object in both Menus being compared is equal
        (returns True for equal contents in their Name and Description Strings and an equal price value)
        False otherwise
    */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Menu) {
            Menu m = (Menu)o;
            if (m.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (!(items[i].equals((MenuItem)m.getItem(i + 1)))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the size of the Menu (how many MenuItem objects are in the items array)
     * @return 
     *      size, the number of MenuItem objects in the array
     **/
    public int size() {
        return size;
    }
    
    /**
     * Adds a MenuItem object to the items array
     * 
     * @param1 item
     *      The MenuItem Object to be added to the Menu
     * @param2 position
     *      The position (1-50) for which to add the Menu object (NOTE: You may only add to whatever the Menu size is)
     * <dt> Precondition:
     *      <dd> <code> position </code> MUST be greater than 0.  Additionally, while the <code> MAX_CAPACITY </code> is 50, you can only add at existing positions or 1 greater than whatever the Menu size is
     * @exception IllegalArgumentException
     *      Indicates item is not an <code> instanceof </code> MenuItem, position is greater than 1 more than the Menu size, or position is less than 0
     * @exception FullListException
     *      Indicates the <code> MenuItem[] </code> items already has 50 <code> MenuItem </code> Objects in it, and cannot take any more
     * <dt> Postcondition
     *      <dd> <code> item </code> will have been added to the Menu.  Since we are asking for positions 1-50 and the array has indexes of 0-49, the index in <code> items </code> at which <code> item </code> has been added will be one less than position.
     *      If the position is less than the max possible position, meaning it is where an existing item currently stands, that item and all items ahead of it will be shifted one index back
     **/
    public void addItem(MenuItem item, int position) throws IllegalArgumentException, FullListException { //ask Esmaili how item shifting will affect later items and how you can place items
        if (size >= MAX_CAPACITY)
            throw new FullListException("List full");
        int i = size;
        if (position > (size + 1) || position < 0 || !(item instanceof MenuItem))
            throw new IllegalArgumentException();
        for (; i >= (position); i--) { //not position - 1 here; if we put 1, it would make position - 1 0, a problem with i - 1 below
            items[i] = items[i - 1];
        }
        items[position - 1] = item;
        if (size < 50 && item != null) {
            size++;
        }
        
    }
    
    /**
     * Removes the <code> MenuItem </code> object at the specified position from the Menu
     * @param position
     *      Specifies which position to remove the object
     * @exception IllegalArgumentException
     *      Denotes that <code> position </code> is greater than the amount of items in <code> items </code>, is less than 0, or there are no <code> MenuItem </code> objects to remove
     * <dt> Postcondition:
     *      The <code> MenuItem </code> at the entered position is removed.  If the position is not the max possible position at which an item can removed, any items behind that <code> MenuItem </code> are shifted forward
     **/
    public void removeItem(int position) throws IllegalArgumentException {
        if ((position  - 1) >= size || (position - 1) < 0 || size == 0)
            throw new IllegalArgumentException();
        items[position - 1] = null;
        for (int i = position - 1; i < size; i++) {
            items[i] = items[i + 1];
        }
        size--;
    }
    
    /**
     * Gets the <code> MenuItem </code> object at the specified position
     * @param position
     *      Represents the position of the desired item
     * @exception1 IllegalArgumentException
     *      Thrown if (position - 1)/array index is greater than <code> size </code> or if (position - 1) is less than 0 or <code> size </code> is equal to 0
     * @exception2 NullPointerException
     *      Thrown in case a <code> position </code> is entered where it is greater than the size and <code> IllegalArgumentException </code> is not thrown
     * @return 
     *      The <code> MenuItem </code> object at the specified <code> position </code>
     **/
    public MenuItem getItem(int position) throws IllegalArgumentException, NullPointerException {
        if ((position - 1) >= size || (position - 1) < 0 || size == 0) {
            throw new IllegalArgumentException();
        } else if (items[position - 1] == null) {
            throw new NullPointerException();
        }
        return items[position - 1];
    }
    
    /**
     * Returns the item with the specified name
     * @param name
     *      String parameter, specifying the name of the desired item
     * @return 
     *      The <code> MenuItem </code> object matching <code> name </code>
     * @exception IllegalArgumentException
     *      Indicates that a MenuItem with that name does not exist/match name
     **/
    public MenuItem getItemByName(String name) throws IllegalArgumentException {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && (items[i].getName()).equals(name)) {
                return items[i];
            }
        }
        throw new IllegalArgumentException();
    }
    
    /**
     * Returns a String description of each <code> MenuItem </code> object in the <code> Menu </code>.  Includes position number, name, description, and price of each <code> MenuItem </code>, where the latter three are obtained by invoking that <code> MenuItem </code> Object's <code> toString() </code> method
     * @return 
     *      A string s, consisting of lines for each item as described above
     * @exception NullPointerException
     *      Thrown in the rare occurrence that a null item is reached
     **/
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            if (items[i] == null)
                throw new NullPointerException();
            s += (i + 1) + ". " + items[i].toString();
        }
        return s;
    }
    
    /**
     * Prints the above String from the toString() method
     **/
    public void printAllItems() {
        System.out.printf("%-3s%-30s%-50s%-15s\n", "#", "Name", "Description", "Price");
        System.out.println(toString());
    }
}
