package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Arrays;

public class ShellSort implements Sort {
    @Override
    public void sort(int[] arr) {
       for (int gap = arr.length >> 1;gap > 0; gap >>= 1) {
           for (int i = gap;i < arr.length; i += gap) {
               int index = i - gap, val = arr[i];
               while (index >= 0 && arr[index] > val) {
                   arr[index+gap] = arr[index];
                   index -= gap;
               }
               arr[index+gap] = val;
           }
       }
        System.out.println(Arrays.toString(arr));
    }
}
