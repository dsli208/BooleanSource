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
public class BooleanSource {

    private double probability;

    public BooleanSource(double p) {
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException();
        }
        probability = p;
    }

    public boolean occurs() {
        return (Math.random() < probability);
    }
}

//Example of use of the BooleanSource object
class CarWash {

    public static void carWash(int washTime, double arrivalProb, int totalTime) throws FullQueueException, EmptyQueueException {
        if (washTime <= 0 || arrivalProb < 0.0 || arrivalProb > 1.0 || totalTime < 0) {
            System.out.println("NO SIMULATION");
            return; //simply for exiting the method
        }
        //simulation variables
        IntQueue cars = new IntQueue();
        BooleanSource arrival = new BooleanSource(arrivalProb);
        int totalWaitTime = 0;
        int carsWashed = 0;
        double avgWaitTime;
        int currentSecond;
        int timeLeftInWasher = 0;

        //simulates each second of time
        for (currentSecond = 1; currentSecond <= totalTime; currentSecond++) {
            //Does a car arrive?
            if (arrival.occurs()) {
                cars.enqueue(currentSecond);
            }
            if ((timeLeftInWasher == 0) && (!cars.isEmpty())) {
                timeLeftInWasher = washTime;
                totalWaitTime += (currentSecond - cars.dequeue()); //difference between the current second and when the car first enqueued
                carsWashed++;
            }
            if (timeLeftInWasher > 0) {
                timeLeftInWasher--;
            }
        }
        //end of for loop, now calculate average statistics
        avgWaitTime = (double) totalWaitTime / carsWashed;
        System.out.println("Average wait time: " + avgWaitTime + " seconds.");
    }
}

class Driver {

    public static void main(String[] args) throws FullQueueException, EmptyQueueException {
        CarWash c = new CarWash();
        try {
            c.carWash(2, 0.35, 50);
        } catch (FullQueueException e) {
            System.out.println("Full queue");
        } catch (EmptyQueueException e1) {
            System.out.println("Empty queue");
        }
        DriveThru d = new DriveThru();
        d.driveThru(20, 50, 0.60, 300);
    }
}

class DriveThru {
    public static void driveThru(int menuTime, int windowTime, double arrivalProb, int totalTime) throws EmptyQueueException, FullQueueException {
        IntQueue driveThruCars = new IntQueue();
        IntQueue menuCars = new IntQueue();
        IntQueue windowCars = new IntQueue();
        BooleanSource arrival = new BooleanSource(arrivalProb);
        int menuOrderTime = 0;
        int windowOrderTime = 0;
        int totalMenuTime = 0;
        int totalWindowTime = 0;
        int totalOrderTime = 0;
        int carsServed = 0;
        double avgWaitTime = 0;
        int waitingTimeLeft = 0;
        int currentSecond;
        for (currentSecond = 0; currentSecond < totalTime; currentSecond++) {
            if (arrival.occurs()) {
                menuCars.enqueue(currentSecond);
                driveThruCars.enqueue(currentSecond); //problem with this IntQueue
                carsServed++;
            }
            if (menuOrderTime == 0 && !menuCars.isEmpty()) {
                menuOrderTime = menuTime;
                totalMenuTime += currentSecond - menuCars.dequeue();
                windowCars.enqueue(currentSecond);
            }
            if (windowOrderTime == 0 && !windowCars.isEmpty()) {
                windowOrderTime = windowTime;
                totalWindowTime += currentSecond - windowCars.dequeue();
                totalOrderTime += currentSecond - driveThruCars.dequeue();
            }
            if (menuOrderTime > 0) {
                menuOrderTime--;
            }
            if (windowOrderTime > 0) {
                windowOrderTime--;
            }
        }
        double averageMenuTime = (double)totalMenuTime / carsServed;
        double averageWindowTime = (double)totalWindowTime / carsServed;
        double averageOrderTime = (double)totalOrderTime / carsServed;
        System.out.print("Average order time at the menu: " + averageMenuTime + " seconds.\nAverage time spent at the window: " + averageWindowTime + " seconds.\nAverage total order time: " + averageOrderTime + " seconds.");
    }
}
