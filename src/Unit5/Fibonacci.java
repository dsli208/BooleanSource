/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit5;

/**
 *
 * @author dsli
 */
public class Fibonacci {
    public int fib(int n) {
        if (n == 0 || n == 1)
            return n;
        int x = fib(n - 1);
        int y = fib(n - 2);
        return (x + y);
    }
    
    
    public static void main(String[] args) {
        
    }
    
    public int[] d = new int[20];
    
    public int fibDynamic(int n) {
        if (n == 0 || n == 1)
            return n;
        if (d[n - 1] == -1) {
            d[n - 1] = fib(n - 1);
        }
        if (d[n - 2] == -1) {
            d[n - 2] = fib(n - 2);
        }
        return (d[n - 1] + d[n - 2]);
    }
}
