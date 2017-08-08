/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * David S. Li
 * 110328771
 * Assignment #4
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class DownloadManager {
    /**
     * Main method
     **/
    public static void main(String[] args) throws EmptyQueueException {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter the number of servers: ");
            int servers = input.nextInt();
            if (servers <= 0) {
                System.out.println("Incorrect input.  Amount of servers CANNOT be 0 or a negative number.  Try again next time.");
                System.exit(0);
            }
            System.out.print("Enter a download speed (in mbps): ");
            int mbps = input.nextInt();
            if (mbps <= 0) {
                System.out.println("Incorrect input.  Mbps CANNOT be 0 or a negative number.  Try again next time.");
                System.exit(0);
            }
            System.out.print("Enter a amount of time: ");
            int time = input.nextInt();
            if (time <= 0) {
                System.out.println("Incorrect input.  Mbps CANNOT be 0 or a negative number.  Try again next time.");
                System.exit(0);
            }
            System.out.print("Enter a number (between 0 and 1) for probability of a Premium download: ");
            double premiumProbability = input.nextDouble();
            if (premiumProbability < 0 || premiumProbability > 1) {
                System.out.println("Incorrect input.  Probability MUST be BETWEEN 0 and 1.  Try again next time.");
                System.exit(0);
            }
            System.out.print("Enter a number (between 0 and 1) for probability of a Regular download: ");
            double regularProbability = input.nextDouble();
            if (regularProbability < 0 || regularProbability > 1) {
                System.out.println("Incorrect input.  Probability MUST be BETWEEN 0 and 1.  Try again next time.");
                System.exit(0);
            }
            DownloadScheduler d = new DownloadScheduler(servers, mbps, time, premiumProbability, regularProbability);
            System.out.println(d.simulate());
        }
        
        catch (InputMismatchException e) {
            System.out.println("You can only input natural numbers.  No numbers less than or equal to 0 or decimal numbers.");
        }
        catch (EmptyQueueException e) {
            System.out.println("EmptyQueueException thrown.  Try again please.");
        }
    }
}
