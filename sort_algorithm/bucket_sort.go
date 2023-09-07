package sort_algorithm

import (
	"fmt"
	"math"
	_sort "sort"
)

type BucketSort []int

func (b *BucketSort) SortImpl() {
	defer fmt.Println(b)
	if len(*b) < 2 {
		return
	}
	min, max := (*b)[0], (*b)[0]
	for _, bb := range *b {
		if bb < min {
			min = bb
		}
		if bb > max {
			max = bb
		}
	}
	const bucketNum = 10
	ranges := int(math.Ceil(float64(max-min+1) / float64(bucketNum)))
	bucketList := make([][]int, bucketNum)
	for _, bb := range *b {
		index := (bb - min) / ranges
		bucketList[index] = append(bucketList[index], bb)
	}
	for i := range bucketList {
		_sort.Ints(bucketList[i])
	}
	*b = []int{}
	for i := 0; i < bucketNum; i++ {
		*b = append(*b, bucketList[i]...)
	}
}
