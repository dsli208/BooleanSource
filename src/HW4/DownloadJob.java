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
 * Sun Lin
 * @author dsli
 */
public class DownloadJob {
    /**
     * Invariants: 
     * downloadSize, an int representing the size of the file to be downloaded
     * downloadSizeRemaining, an int remaining how much still needs to be downloaded
     * timeRequested, an int denoting at what time this download was created
     * isPremium, a boolean denoting whether this is a premium download or not
     * id, an int denoting whether this was the first, second, third, etc. download created
     **/
    private int downloadSize;
    private int downloadSizeRemaining;
    private int timeRequested;
    private boolean isPremium;
    private int id;

    /**
     * Returns <code> downloadSizeRemaining </code>
     * @return
     *      <code> downloadSizeRemaining </code>, denoting how much is left to be downloaded
     **/
    public int getDownloadSizeRemaining() {
        return downloadSizeRemaining;
    }
    
    /**
     * Decreases <code> downloadSizeRemaining </code> by the given parameter <code> downloadDecrement </code>
     * @param downloadDecrement
     *      Amount to decrease the <code> downloadSizeRemaining </code> by.  When used in conjunction with the DownloadScheduler, it matches <code> downloadSpeed </code> in that class
     * <dt> Postcondition </dt>
     *      <code> downloadSizeRemaining </code> is decreased by <code> downloadDecrement </code>
     **/
    public void continueDownloading(int downloadDecrement) {
        this.downloadSizeRemaining -= downloadDecrement;
    }
    
    /**
     * Returns the time that this download was created and queued
     * @return
     *      <code> timeRequested </code>, denoting when this download was created and queued
     **/
    public int getTimeRequested() {
        return timeRequested;
    }

    /**
     * Returns whether this is a premium download or not
     * @return
     *      <code> isPremium </code>, denoting whether this download is premium or not
     **/
    public boolean isPremium() {
        return isPremium;
    }

    /**
     * Returns id, the job number
     * @return
     *      <code> id </code>, a job number representative of when the job was created
     **/
    public int getId() {
        return id;
    }

    /**
     * Creates an instance of DownloadJob
     * @param downloadSize
     *      Size of the download file
     * @param timeRequested
     *      A timestamp as to when this download was requested and queued
     * @param isPremium
     *      Denotes whether this is a Premium download
     * @param id
     *      Denotes the job number
     * <dt> Postcondition </dt>
     *      A DownloadJob is created, with the invariants set accordingly based on the parameters
     **/
    public DownloadJob(int downloadSize, int timeRequested, boolean isPremium, int id) {
        this.id = id;
        this.downloadSize = downloadSize;
        this.downloadSizeRemaining = downloadSize;
        this.isPremium = isPremium;
        this.timeRequested = timeRequested;
    }
    
    /**
     * Returns the size of the download file
     * @return
     *      <code> downloadSize </code>, representing the total size of this file
     **/
    public int getDownloadSize() {
        return downloadSize;
    }
    
    /**
     * Returns how much of the file is left to be downloaded
     * @return
     *      <code> downloadSizeRemaining </code>, denotes how much of the file still needs to be downloaded before the download is complete
     **/
    public int getSizeRemaining() {
        return downloadSizeRemaining;
    }
    
    /**
     * Returns an abbreviated String representation of this DownloadJob, used for listing the Job when listing a Job queue
     * @return
     *      Returns a abbreviated String, containing the id/job number and the file size
     **/
    public String toQueueString() {
        return "[#" + this.id + " : " + this.downloadSize + " mb ] ";
    }
    
    /**
     * Returns a String representation of this DownloadJob
     * @return
     *      Returns a String representation, containing id (job number), file download size, download size remaining, timestamp as to when this download was requested, and premium status
     **/
    @Override
    public String toString() {
        return "[#" + this.id + ", " + this.downloadSize + " mb total, " + this.downloadSizeRemaining + " mb remaining, Request Time: " + this.timeRequested + ", " + (this.isPremium()? "Premium" : "Regular") +  " ]";
    }
}
