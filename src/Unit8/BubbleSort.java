/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit8;
import Unit8.SelectionSort;
/**
 *
 * @author dsli
 */
public class BubbleSort {
    public static void sort(int[] data, int n) {
        int i, j;
        for (i = 0; i <= n - 2; i++) {
            for (j = n - 1; j > i; j--)
                if (data[j] < data[j - 1])
                    swap(data, j, j - 1);
        }
    }
}
