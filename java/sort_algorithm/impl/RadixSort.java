package sort_algorithm.impl;
import sort_algorithm.Sort;
import java.util.*;

public class RadixSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(arr[0]);
        int len = (max+"").length();
        final int BucketNum = 10;
        List<Integer>[] buckets = new List[BucketNum];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int i = 1; i <= len; i++) {
            for (int k : arr) {
                int index = (int) (k / Math.pow(10, i - 1) % BucketNum);
                buckets[index].add(k);
            }
            int index = 0;
            for (List<Integer> bucket : buckets) {
                for (Integer integer : bucket) {
                    arr[index++] = integer;
                }
            }
            for (List<Integer> bucket : buckets) {
                bucket.clear();
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
