/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW4;

/**
 * David S. Li
 * 110328771
 * Assigment #4
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class DownloadScheduler {
    /**
     * Invariants:
     * regularQ, a DownloadQueue storing "Regular" downloads
     * premiumQ, a DownloadQueue storing "Premium" downloads
     * currentTime, an int storing the current timestep
     * simulationEndTime, denotes the total amount of timesteps for which the simulation is supposed to run
     * random, a DownloadRandomizer object that randomly generates downloads of varying sizes
     * currentJob[], an array representing the amount of servers that can process downloads at a given time
     * downloadSpeed, denoting the download speed
     **/
    private DownloadQueue regularQ;
    private DownloadQueue premiumQ;
    private int currentTime;
    private int simulationEndTime;
    private DownloadRandomizer random;
    private DownloadJob[] currentJob;
    private int downloadSpeed;

    /**
     * Returns a String, showing how downloads were processed in the given timeframe, as commented below
     * @exception EmptyQueueException
     *      In the event we try to dequeue from a queue and it is empty, this is thrown
     * @return
     *      A String that represents a simulation of how many downloads are processed during a given timeframe (from timestep 1 to the value of <code> simulationEndTime </code>)
     **/
    public String simulate() throws EmptyQueueException {
        //Invariants as marked on each line
        int totalData = 0, totalPremiumData = 0, totalRegularData = 0; //Total data of jobs completed
        //The first variable on this line totalJobs, is a tracker variable to set for the id of each new job enqueued
        //The latter three variables represent total amount of jobs completed (the former two of those denote how many regular and premium jobs are completed, respectively)
        int totalJobs = 0, totalRegularJobs = 0, totalPremiumJobs = 0, totalJobsCompleted = 0;
        int totalPremiumWaitTime = 0, totalRegularWaitTime = 0;
        String simulation = "Simulation: \n\n";
        while (currentTime <= simulationEndTime) {
            //Printing timestep
            simulation += "Timestep " + currentTime + ":\n\n";
            //New Premium Job
            simulation += "New Premium Job: ";
            int newPremium = random.getPremium();
            if (newPremium != -1) {
                totalJobs++;
                premiumQ.enqueue(new DownloadJob(newPremium, currentTime, true, totalJobs));
                simulation += "Job #" + totalJobs + ".  Size: " + newPremium + " mb" + '\n';
            } else {
                simulation += "n/a" + '\n';
            }
            //New Regular Job
            simulation += "New Regular Job: ";
            int newRegular = random.getRegular();
            if (newRegular != -1) {
                totalJobs++;
                regularQ.enqueue(new DownloadJob(newRegular, currentTime, false, totalJobs));
                simulation += "Job #" + totalJobs + ".  Size: " + newRegular + "mb" + '\n';
            } else {
                simulation += "n/a" + '\n';
            }
            //Evaluate the currentJob array, are there any empty spots?  If so, fill them up by dequeueing from the premium/regular queues
            for (int i = 0; i < currentJob.length; i++) {
                //Check if there is a job occupying the server in the array
                if (currentJob[i] != null) {
                    //Continue to download at the specified rate (subtract that mbps from the download size)
                    currentJob[i].continueDownloading(downloadSpeed);
                    //Check to see if that job is now complete.  If that job IS complete
                    if (currentJob[i].getDownloadSizeRemaining() <= 0) { //meaning the job was completed
                        //add that data to the total data of completed downloads, and increment totalJobsCompleted
                        totalData += currentJob[i].getDownloadSize();
                        totalJobsCompleted++;
                        //Do the same for the premium/regular variables based on if that job is premium or not
                        if (currentJob[i].isPremium()) {
                            totalPremiumData += currentJob[i].getDownloadSize();
                            totalPremiumWaitTime += currentTime - currentJob[i].getTimeRequested();
                            totalPremiumJobs++;
                        }
                        else {
                            totalRegularData += currentJob[i].getDownloadSize();
                            totalRegularWaitTime += currentTime - currentJob[i].getTimeRequested();
                            totalRegularJobs++;
                        }
                        
                        //Nullify that job, making room for the next job in the premium/regular queue
                        currentJob[i] = null;
                    }
                }
                //Now check to see if the server is empty.  Note: This is not an "else" statement, as the server could have just emptied due to the download occupying it just finishing
                if (currentJob[i] == null) {
                    //First, dequeue anything in the premium queue
                    if (premiumQ.isEmpty() == false) {
                        currentJob[i] = premiumQ.dequeue();
                    } //if there is room, dequeue from the regular queue
                    else if (regularQ.isEmpty() == false) {
                        currentJob[i] = regularQ.dequeue();
                    }
                }
            }
            simulation += "Regular Queue: ";
            //Now, append the queue information after any new jobs have been created and jobs have been dequeued as necessary
            if (regularQ.isEmpty() == false) {
                DownloadJob traverseJob = regularQ.peek();
                //Invoke the toString() method in each queue
                simulation += regularQ.toString();
            } else {
                simulation += "empty";
            }
            simulation += '\n' + "Premium Queue: ";
            if (premiumQ.isEmpty() == false) {
                DownloadJob traverseJob = premiumQ.peek();
                simulation += premiumQ.toString();
            } else {
                simulation += "Empty";
            }
            simulation += '\n' + "Server status: ";
            for (int i = 0; i < currentJob.length; i++) {
                int n = i + 1;
                if (currentJob[i] == null) {
                    simulation += '\n' + "Server " + n + ": idle";
                } else {
                    simulation += '\n' + "Server " + n + ": " + currentJob[i].toString();
                }
            }
            simulation += "\n\n"; //Create a "gap" in the console between this timestep and the next timestep
            currentTime++;
        }
        //Summation of the whole simulation process
        String summary ="Simulation Ended.  Summary below: " + '\n';
        summary += "Total Jobs Served: " + totalJobsCompleted + '\n';
        summary += "Total Premium Jobs Served: " + totalPremiumJobs + '\n';
        summary += "Total Regular Jobs Served: " + totalRegularJobs + '\n';
        summary += "Total Data Served: " + totalData + '\n';
        summary += "Total Premium Data Served: " + totalPremiumData + '\n';
        summary += "Total Regular Data Served: " + totalRegularData + '\n';
        summary += "Average Premium Wait Time: " + (totalPremiumWaitTime / totalPremiumJobs) + '\n';
        summary += "Average Regular Wait Time: " + (totalRegularWaitTime / totalRegularJobs) + '\n';
        //Return one big String at the very end
        return simulation + "\n\n" + summary;
    }

    /**
     * Creates an instance of a DownloadScheduler, based on the given parameters
     * @param servers
     *      The amount of servers used for this DownloadScheduler (length of <code> currentJob </code>)
     * @param mbps
     *      The download speed (<code> downloadSpeed </code> is initialized to this variable)
     * @param time
     *      The running time of this simulation (<code> simulationEndTime </code> is initialized to this variable)
     * @param premiumProbability
     *      The probability that a premium download job will be created
     * @param regularProbability
     *      The probability a regular download will be created
     * <dt> Postcondition </dt>
     *      <code> currentJob </code>, <code> downloadSpeed </code>, and <code> simulationEndTime </code> are initialized based on <code> servers </code>, <code> mbps</code>, and <code> time </code> respectively.
     *      <code> currentTime </code> is initialized to 1, and <code> premiumQ </code> and <code> regularQ </code> are initialized to new DownloadQueue objects.  <code> random </code> is initialized based on the values of <code> premiumProbability </code> and <code> regularProbability </code>
     **/
    public DownloadScheduler(int servers, int mbps, int time, double premiumProbability, double regularProbability) {
        this.premiumQ = new DownloadQueue();
        this.regularQ = new DownloadQueue();
        this.currentJob = new DownloadJob[servers];
        this.downloadSpeed = mbps;
        this.currentTime = 1;
        this.simulationEndTime = time;
        this.random = new DownloadRandomizer(premiumProbability, regularProbability);
    }
}
