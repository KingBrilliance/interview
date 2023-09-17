package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Arrays;

public class InsertSort implements Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i, tmp = arr[i];
            while (index - 1 >= 0 && arr[index] > tmp ) {
                arr[index] = arr[index - 1];
                --index;
            }
            arr[index] = tmp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
