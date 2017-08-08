/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prelude;

/**
 *
 * @author dsli
 */
public class OtherReview {
    
}

interface Comparable {
    public abstract int compareTo(Object o);
}

class SampleObject implements Comparable {
    public int value = 2;
    @Override
    public int compareTo(Object o) {
        if (((SampleObject)o).value > value)
            return 1;
        else if (((SampleObject)o).value < value)
            return -1;
        return 0;
    }
}

class SampleSubclass extends SampleObject{
    @Override
    public int compareTo(Object o) {
        if (((SampleSubclass)o).value > value)
            return 1;
        else if (((SampleSubclass)o).value < value)
            return -1;
        return 0;
    }
}
