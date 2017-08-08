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
public class MenuItem implements Cloneable {
    /*Invariants:
    - name is a String object with the name of the MenuItem object
    - description is a String describing the MenuItem object
    - price is a double value, containing the price of the item (when displayed, it is rounded to two decimal places)
    */
    private String name;
    private String description;
    private double price;
    /**
     * Returns an Object of type <code> MenuItem </code>
     * <dt> Postcondition:
     *      The MenuItem object is created using the variables <code> name, description, and price </code>
     **/
    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    /**
     * Returns the name of the <code> MenuItem </code> object
     * @return
     *      Returns <code> name </code>
     **/
    public String getName() {
        return name;
    }
    /**
     * Returns the description of the <code> MenuItem </code> Object
     * @return 
     *      Returns <code> description </code>
     **/
    public String getDescription() {return description;}
    /**
     * Returns the price of the <code> MenuItem </code> Object
     * @return 
     *      Returns price
     **/
    public double getPrice() {
        return price;
    }
    /**
     * Changes the name of this Object
     * @param name
     *      The new desired name for this Object
     * <dt> Postcondition:
     *      <code> name </code> will be changed from the existing name to the contents of the parameter
     **/
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Changes the description of this Object
     * @param description
     *      The new desired description for this Object
     * <dt> Postcondition:
     *      <code> description </code> will be dereferenced from the old object and replaced to match the parameter
     **/
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Changes the price of this Object
     * @param price
     *      The new desired price
     * <dt> Postcondition:
     *      <code> price </code> will be updated to reflect the value of the parameter <code> price </code>
     **/
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Compares this <code> MenuItem </code> Object with another Object <code> o </code>
     * @param o
     *      An Object to be compared with this <code> MenuItem </code>
     * @return 
     *      True if <code> o </code> is of type MenuItem and its <code> name, description, price </code> match the respective properties of this <code> MenuItem </code>.  False otherwise
     **/
    @Override
    public boolean equals(Object o) {
        if (o instanceof MenuItem) {
            if (((MenuItem)o).getName().equals(name) && ((MenuItem)o).getDescription().equals(description) && ((MenuItem)o).getPrice() == price)
                return true;
        }
        return false;
    }
    /**
     * Creates another <code> MenuItem </code> Object that is an exact copy of this <code> MenuItem </code>
     * @return 
     *      An Object, casted to type <code> MenuItem </code>.  It invokes the constructor, setting <code> name, description, price </code> to the same contents/values as in this Object
     **/
    @Override
    public Object clone() {
        //MenuItem m = new MenuItem(name, description, price);
        return ((MenuItem)new MenuItem(name, description, price));
    }
    /**
     * Creates a String representation of this <code> MenuItem </code> object, including the name, description, and price
     * @return 
     *      A formatted description of the <code> MenuItem </code> object as described above
     **/
    @Override
    public String toString() {
        return String.format("%-30s%-50s%-15s\n", name, description, "$" + String.format("%.2f", price));
    }
}
