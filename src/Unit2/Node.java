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
public class Node {
    private Object data;
    private Node link;
    
    public Node(Object initialData) {
        data = initialData;
        link = null;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object newData) {
        data = newData;
    }
    
    public void setLink(Node link) {
        this.link = link;
    }
}
