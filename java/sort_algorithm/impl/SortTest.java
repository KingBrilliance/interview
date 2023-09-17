package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Random;

public class SortTest {
    static void test(Sort s, int[] arr) {
        s.sort(arr);
    }

    public static void main(String[] args) {
        int[] arr = new int[20];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        test(new BubbleSort(), arr);
        test(new SelectSort(), arr);
        test(new InsertSort(), arr);
        test(new ShellSort(), arr);
        test(new MergeSort(), arr);
        test(new QuickSort(), arr);
        test(new HeapSort(), arr);
        test(new CountSort(), arr);
        test(new BucketSort(), arr);
        test(new RadixSort(), arr);
    }
}
