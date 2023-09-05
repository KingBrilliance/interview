package sort_algorithm

import "fmt"

type InsertSort []int

func (i InsertSort) SortImpl() {
	for j := 1; j < len(i); j++ {
		k, tmp := j-1, i[j]
		for k >= 0 && i[k] > tmp {
			i[k+1] = i[k]
			k--
		}
		i[k+1] = tmp
	}
	fmt.Println(i)
}

func (i InsertSort) SortImpl2() {
	for j := 1; j < len(i); j++ {
		index, val := j, i[j]
		for index-1 >= 0 && val < i[index-1] {
			i[index] = i[index-1]
			index--
		}
		i[index] = val
	}
	fmt.Println(i)
}
