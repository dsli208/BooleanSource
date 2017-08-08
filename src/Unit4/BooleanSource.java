/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit4;

/**
 *
 * @author dsli
 */
public class BooleanSource {
    private double probability;
    public BooleanSource(double p) {
        if (p < 0 || p > 1.0)
            throw new IllegalArgumentException();
        probability = p;
    }
    public boolean occurs() {
        return (Math.random() < probability);
    }
    
    public static void carWash(int washTime, double arrivalProb, int totalTime) throws FullQueueException, EmptyQueueException {
        if (washTime <= 0 || arrivalProb < 0.0) {
            System.out.println("NO SIMULATION");
            return;
        }
        
        IntQueue cars = new IntQueue();
        BooleanSource arrival = new BooleanSource(arrivalProb); //Has applications, like road counters as to how many cars pass at a given time
        int totalWaitTime = 0;
        int carsWashed = 0;
        double avgWaitTime;
        int currentSecond;
        int timeLeftInWasher = 0;
        //For loop that simulates each second of time
        for (currentSecond = 1; currentSecond <= totalTime; currentSecond++) { //Note that when currentSecond reaches totalTime, there may be cars in the IntQueue that aren't serviced
            if (arrival.occurs())
                cars.enqueue(currentSecond);
            if ((timeLeftInWasher == 0) && (!cars.isEmpty())) {
                timeLeftInWasher = washTime;
                totalWaitTime += currentSecond - cars.dequeue();
                carsWashed++;
            }
            if (timeLeftInWasher > 0)
                timeLeftInWasher--;
        }
        while (!cars.isEmpty()) { //Extra provision, serving any customers in the queue after closing time
            //NOTE: If we have to include event 1, make sure to set the probability to 0 so no new arrivals occur
            if ((timeLeftInWasher == 0) && (!cars.isEmpty())) {
                timeLeftInWasher = washTime;
                totalWaitTime += currentSecond - cars.dequeue();
                carsWashed++;
            }
            if (timeLeftInWasher > 0)
                timeLeftInWasher--;
        }
        
        avgWaitTime = (double)totalWaitTime / carsWashed;
        System.out.println("Avg wait time = " + avgWaitTime + " seconds.");
    }
}
