package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CountSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int max = IntStream.of(arr).max().getAsInt(), min = IntStream.of(arr).min().getAsInt();
        final int diff = max - min + 1;
        int[] counts = new int[diff];
        for (int a: arr) {
            counts[a-min]++;
        }
        arr = new int[arr.length];
        for (int i = 0, j = 0; i < counts.length; i++) {
            while(counts[i]-- > 0) {
                arr[j++] = i + min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
