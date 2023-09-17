package sort_algorithm.impl;

import sort_algorithm.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BucketSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt(), max = IntStream.of(arr).max().getAsInt();
        final int bucketNum = 10;
        double range = (max - min + 1)*1.0 / bucketNum;
        List<Integer>[] buckets = new List[bucketNum];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int i : arr) {
            int index = (int)((i - min) / range);
            buckets[index].add(i);
        }
        for (List<Integer> bucket : buckets) {
            bucket.sort(Integer::compareTo);
        }
        for (int i = 0, j = 0; i < buckets.length; i++) {
            for (Integer integer : buckets[i]) {
                arr[j++] = integer;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
