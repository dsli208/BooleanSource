/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit4;
//Note, not self implemented Queues or LinkedLists or Stacks, rather taken from Java API
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author dsli
 */
public class Recitation4Problem5 {
    public int calculate(String string) {
    //Create the queue and stack
    Queue<Integer> q = new LinkedList();
    Stack<Integer> s = new Stack();
    //Break up the String into the numbers
    String[] split = string.split(" ");
    int[] intArray = new int[split.length];
    for (int i = 0; i < intArray.length; i++) {
        intArray[i] = Integer.parseInt(split[i]);
    }
    for (int j: intArray) {
        if (j != 1 && isPerfectSquare(j))
        else if (j % 2 == 0)
            s.push(j);
        else if (j % 2 != 0)
            q.add(j);
    }
}
