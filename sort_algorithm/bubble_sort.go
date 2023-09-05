package sort_algorithm

import "fmt"

type Sort interface {
	SortImpl()
}

type BubbleSort []int

func (b BubbleSort) SortImpl() {
	for i := 0; i < len(b); i++ {
		for j := i + 1; j < len(b); j++ {
			if b[i] > b[j] {
				b[j], b[i] = b[i], b[j]
			}
		}
	}
	fmt.Println(b)
}
