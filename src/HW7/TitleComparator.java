/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW7;

import java.util.Comparator;

/**
 * David S. Li
 * 110328771
 * Assignment #7
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class TitleComparator implements Comparator {
    /**
     * Returns an int value from a title Comparison between the two passed Movie objects
     * 
     * @param o1
     * The first Movie object being passed
     * @param o2
     * The second Movie object being passed
     * 
     * <dt> Precondition </dt>
     * <code> o1 </code> and <code> o2 </code> MUST be instances of Movie objects, or else an UnsupportedOperationException (See below) will be thrown
     * 
     * @return
     *      An int value based on the compareTo method for Strings (positive denotes this String should go after the other, and vice versa, 0 denotes they are the same)
     * 
     * @exception UnsupportedOperationException
     *      Thrown if <code> o1 </code> or <code> o2 </code> is NOT an instance of a Movie
     **/
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Movie && o2 instanceof Movie) {
            Movie m1 = (Movie)o1;
            Movie m2 = (Movie)o2;
            String title1 = m1.getTitle().toLowerCase();
            String title2 = m2.getTitle().toLowerCase();
            return title1.compareTo(title2);
        }
        throw new UnsupportedOperationException("Bad input.");
    }
    
}
