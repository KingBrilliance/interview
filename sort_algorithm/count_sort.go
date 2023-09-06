package sort_algorithm

import "fmt"

type CountSort []int

func (c CountSort) SortImpl() {
	if len(c) < 2 {
		return
	}
	min, max := c[0], c[0]
	for _, cc := range c {
		if cc < min {
			min = cc
		}
		if cc > max {
			max = cc
		}
	}
	d := max - min
	arr := make([]int, d+1)
	for _, cc := range c {
		arr[cc-min]++
	}
	j := 0
	for i, cc := range arr {
		for ; cc > 0; cc-- {
			c[j] = i + min
			j++
		}
	}
	fmt.Println(c)
}
