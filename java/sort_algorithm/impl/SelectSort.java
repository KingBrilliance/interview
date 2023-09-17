package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Arrays;

public class SelectSort implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i], index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            arr[i] = min;
            int tmp = arr[i];
            arr[index] = tmp;
        }
        System.out.println(Arrays.toString(arr));
    }

}
