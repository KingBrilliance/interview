package sort_algorithm

import (
	"fmt"
	"math"
)

type MergeSort []int

func (m *MergeSort) SortImpl() {
	*m = help(*m)
	fmt.Println(m)
}

func help(arr MergeSort) MergeSort {
	if len(arr) < 2 {
		return arr
	}
	mid := len(arr) / 2
	r := help(arr[:mid])
	l := help(arr[mid:])
	res := MergeSort{}
	for i, j := 0, 0; i < len(r) || j < len(l); {
		vi, vj := math.MaxInt32, math.MaxInt32
		if i < len(r) {
			vi = r[i]
		}
		if j < len(l) {
			vj = l[j]
		}
		if vj > vi {
			res = append(res, vi)
			i++
		} else {
			res = append(res, vj)
			j++
		}
	}

	return res
}
