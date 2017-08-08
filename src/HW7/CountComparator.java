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
public class CountComparator implements Comparator {
    /**
     * Returns an int value, denoting the count comparison between two ACTOR Objects
     * 
     * @param o1
     * Denotes the first Actor being passed
     * @param o2
     * Denotes the second Actor being passed
     * 
     * <dt> Precondition </dt>
     * For this implementation of the compare method in the Comparator interface, <code> o1 </code> and <code> o1 </code> MUST be instances of Actor objects
     * 
     * @return 
     *      An int value denoting the comparison between <code> o1 </code> and <code> o2 </code> (0 denotes they are the same, 1 denotes <code> o1 </code> is greater...)
     * @exception UnsupportedOperationException
     *      Thrown if <code> o1 </code> or <code> o2 </code> are NOT instances of Actor objects
     **/
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Actor && o2 instanceof Actor) {
            int count1 = ((Actor)o1).getCount();
            int count2 = ((Actor)o2).getCount();
            if (count1 < count2) {
                return -1;
            }
            else if (count1 > count2) {
                return 1;
            }
            return 0;
        }
        throw new UnsupportedOperationException("Bad object input");
    }
}
