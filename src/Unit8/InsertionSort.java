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
public class InsertionSort {

    public static void sort(int[] data, int n) {
        int i, j, item;
        for (i = 1; i <= n - 1; i++) {
            item = data[i];
            j = i;
            while (j > 0 && data[j - 1] > item) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = item;
        }
    }
}
