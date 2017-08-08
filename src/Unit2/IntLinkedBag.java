/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit2;

/**
 *
 * @author dsli
 */
public class IntLinkedBag implements Cloneable {
    private IntList data;
    private int manyItems;
    
    public IntLinkedBag() {
        manyItems = 0;
        data = new IntList();
    }
    
    public int getCapacity() {
        return Integer.MAX_VALUE; //There is no max capacity, other than the max an int can be
    }
    
    public int size() {
        return manyItems;
    }
    
    public void ensureCapacity(int minimumCapacity) {
        //No work needed, this linked list class has infinite capacity
    }
    
    public void add(int element) {
        data.addNewHead(element);
        manyItems++;
    }
    
    public int countOccurrences(int target) throws EmptyListException{
        int answer = 0;
        int index;
        data.resetCursor();
        for (index = 0; index < manyItems; index++) {
            if (target == data.getNodeData()) //Note: counts occurrences of the same references, not necessarily that the Objects are just equal in content
                answer++;
            data.advanceCursor();
        }
        return answer;
    }
}
