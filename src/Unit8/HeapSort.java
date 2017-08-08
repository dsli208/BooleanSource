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
public class HeapSort {

    public static void sort(int[] data, int n) {
        int lastHeapPosition;
        makeHeap(data, n);
        lastHeapPosition = n - 1;
        while (lastHeapPosition > 0) {
            swap(data, 0, lastHeapPosition);
            reheapify(data, lastHeapPosition);
            lastHeapPosition--;
        }
    }

    public static void makeHeap(int[] data, int n) {
        int i, next;
        for (i = 1; i <= n - 1; i++) {
            next = i;
            while (next != 0 && data[next] > data[parent(next)]) {
                swap(data, next, parent(next));
                next = parent(next); //"parent" method is assumed, how to implement?
            }
        }
    }

    public static void reheapify(int[] data, int heapsize) {
        int position = 0, childPos;
        while (position * 2 + 1 < heapsize) {
            childPos = position * 2 + 1;
            if (childPos < heapsize - 1 && data[childPos + 1] > data[childPos]) {
                childPos++;
            }
            if (data[position] < data[childPos]) {
                swap(data, position, childPos);
                position = childPos;
            } else {
                return;
            }
        }
    }

    public static void swap(int[] data, int a, int b) {
        int temp = data[b];
        data[b] = data[a];
        data[a] = temp;
        return;
    }

    public static int parent(int i) {
        return (i - 1) / 2;
    }

    public static void efficientMakeHeap(int[] data, int n) {
        int i, child, position = 0;
        for (i = (n - 2) / 2; i >= 0; i--) {
            position = i;
            while (position * 2 + 1 < n) {
                child = position * 2 + 1;
                if (child < n - 1 && data[child + 1] > data[child]) {
                    child++;
                }
                if (data[child] > data[position]) {
                    swap(data, child, position);
                    position = child;
                } else {
                    break;
                }
            }
        }
    }
}
