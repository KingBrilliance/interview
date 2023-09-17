package sort_algorithm

import (
	"fmt"
	"math"
)

type RadixSort []int

func (r *RadixSort) SortImpl() {
	defer fmt.Println(r)
	if len(*r) < 2 {
		return
	}

	max := (*r)[0]
	for _, rr := range *r {
		if max < rr {
			max = rr
		}
	}
	num := 0
	for max > 0 {
		num++
		max /= 10
	}

	const BucketNum = 10

	buckets := make([][]int, BucketNum)
	for i := 1; i <= num; i++ {
		for _, rr := range *r {
			radio := rr / int(math.Pow(10, float64(i)-1)) % 10
			buckets[radio] = append(buckets[radio], rr)
		}

		*r = []int{}
		for j := 0; j < 10; j++ {
			*r = append(*r, buckets[j]...)
			buckets[j] = []int{}
		}
	}
}
