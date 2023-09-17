package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class QuickSort implements Sort {
    @Override
    public void sort(int[] arr) {
        Deque<int[]> stack = new LinkedList<>();
        while (!stack.isEmpty()) {
            int[] x = stack.pop();
            int l = x[0], r = x[1];
            if (l < r) {
                int mid = partition(arr, l, r);
                if (mid - 1 > l) {
                    stack.push(new int[]{l, mid - 1});
                }
                if (mid + 1 < r) {
                    stack.push(new int[]{mid + 1, r});
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    int partition(int[] arr, int l, int r) {
        int pivot = arr[l], i = l + 1, j = r;
        while(i <= j) {
            while(i <= j && arr[i] <= pivot) i++;
            while(j >= i && arr[j] >= pivot) j--;
            if (i >= j) break;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        arr[l] = arr[j];
        arr[j] = pivot;
        return j;
    }
}
