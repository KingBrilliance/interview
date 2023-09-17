package sort_algorithm

import "fmt"

type II = int

type SelectSort []int

func (s SelectSort) SortImpl() {
	for i := 0; i < len(s); i++ {
		index := i
		for j := i + 1; j < len(s); j++ {
			if s[index] > s[j] {
				index = j
			}
		}
		s[i], s[index] = s[index], s[i]
	}

	fmt.Println(s)
}
