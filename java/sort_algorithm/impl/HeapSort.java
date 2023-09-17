package sort_algorithm.impl;

import sort_algorithm.Sort;

public class HeapSort implements Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0 ; --i) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, 0, arr.length - i);
        }
    }
    public void heapify(int[] arr, int l, int r) {
        int val = arr[l];
        for (int i = 2 * l + 1; i < r; i = 2 * l + 1) {
            if (i + 1 < r && arr[i+1] > arr[i]) i++;
            if (arr[i] <= val) break;
            arr[l] = arr[i];
            l = i;
        }
        arr[l] = val;
    }
}
