/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW4;

/**
 * David S. Li
 * 110328771
 * Assignment #4
 * CSE 214 R05
 * Frank Migliorno
 * SUn Lin
 * @author dsli
 */
public class DownloadNode {
    //linked list structure that forms the basis of a DownloadQueue
    /**
     * Invariants:
     * data, the DownloadJob wrapped in this DownloadNode
     * link, a DownloadNode reference to the next DownloadNode object
     **/
    private DownloadJob data;
    private DownloadNode link;
    
    /**
     * Returns the data wrapped in this node
     * @return
     *      <code> data </code>, the DownloadJob wrapped in this node
     **/
    public DownloadJob getData() {
        return data;
    }

    /**
     * Changes the data DownloadJob wrapped in this node (NOTE: This method is NEVER invoked in this assignment)
     * @param data
     *      The new data reference to be wrapped in this DownloadNode
     * <dt> Postcondition: </dt>
     *      The <code> data </code> reference is now set to the DownloadJob parameter
     **/
    public void setData(DownloadJob data) {
        this.data = data;
    }

    /**
     * Returns the reference to the DownloadNode referencing the next node in the list
     * @return
     *      <code> link </code>, the reference to the next DownloadNode in the list
     **/
    public DownloadNode getLink() {
        return link;
    }
    
    /**
     * Sets the link reference of this DownloadNode object
     * @param link
     *      The DownloadNode reference that will become the DownloadNode after this one in the list
     * <dt> Postcondition </dt>
     *      The link of this node is set to the DownloadNode passed in the parameter
     **/
    public void setLink(DownloadNode link) {
        this.link = link;
    }
    
    /**
     * Creates an instance of a DownloadNode
     * @param job
     *      The DownloadJob to be wrapped in this newly created node
     * <dt> Postcondition </dt>
     *      <code> data </code> is initialized to the <code> job </code> parameter, while link is initialized to null (this will become the last node in the queue, when a new rear is appended, link will be set to that node)
     **/
    public DownloadNode(DownloadJob job) {
        this.data = job;
        link = null;
    }
    
    
}
