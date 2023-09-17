package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.Arrays;

public class MergeSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int[] res = help(arr);
        System.out.println(Arrays.toString(res));
    }

    public int[] help(int[] arr) {
        if (arr.length < 2) return arr;
        int mid = arr.length >> 1;
        int[] l = help(Arrays.copyOfRange(arr, 0, mid)), r = help(Arrays.copyOfRange(arr, mid, arr.length));
        int[] res = new int[arr.length];
        for (int i = 0, j = 0, index = 0;i < l.length || j < r.length;) {
            int rv = j < r.length ? r[j] : Integer.MAX_VALUE, lv = i < l.length ? l[i] : Integer.MAX_VALUE;
            if (rv < lv) {
                res[index++] = rv;
                j++;
            } else {
                res[index++] = lv;
                i++;
            }
        }

        return res;
    }
}
