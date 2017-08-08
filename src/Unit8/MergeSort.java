/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit8;

/**
 *
 * @author dsli
 */
public class MergeSort {
    public static void sort(int[] data, int first, int n) {
        int n1, n2;
        if (n > 1) {
            n1 = n / 2; n2 = n - n1;
            sort(data, first, n1);
            sort(data, first + n1, n2);
            merge(data, first, n1, n2); //how to implement "merge"?
        }
    }
}
