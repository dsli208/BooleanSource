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
public class YearComparator implements Comparator {

    /**
     * Returns an int value from a year Comparison between the two passed Movie objects
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
     *      An int value based on which <code> year </code> is greater (positive denotes <code> o1 </code> was made later, and vice versa, 0 denotes they were made in the same year)
     * 
     * @exception UnsupportedOperationException
     *      Thrown if <code> o1 </code> or <code> o2 </code> is NOT an instance of a Movie
     **/
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Movie && o2 instanceof Movie) {
            Movie m1 = (Movie)o1;
            Movie m2 = (Movie)o2;
            if (m1.getYear() < m2.getYear())
                return -1;
            else if (m1.getYear() > m2.getYear())
                return 1;
            return 0;
        }
        throw new UnsupportedOperationException("Bad object input.");
    }
    

}
