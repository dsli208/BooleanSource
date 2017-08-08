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
public class SelectionSort {
    public static void sort(int[] data, int n) {
        int i, j, minLocation;
        for (i = 0; i <= n - 2; i++) {
            minLocation = i;
            for (j = i + 1; j <= n - 1; j++) {
                if (data[j] < data[minLocation])
                    minLocation = j;
            }
        swap(data, minLocation, i);
        }
    }
    
    public static void sort2(int[] data, int n) {
        int i, j;
        for (i = 0; i <= n - 2; i++) {
            for (j = i + 1; j <= n - 1; j++) {
                if (data[j] < data[i])
                    swap(data, i, j);
            }
        }
    }
    
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
