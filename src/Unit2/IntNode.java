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
public class IntNode {
    private int data;
    private IntNode link;
    
    public IntNode(int initialData) {
        data = initialData;
        link = null;
    }
    
    public int getData() {
        return data;
    }
    public IntNode getLink() {
        return link;
    }
    
    public void setData(int newData) {
        data = newData;
    }
    public void setLink(IntNode newLink) {
        link = newLink;
    }
}
