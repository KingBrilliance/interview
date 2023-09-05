package sort_algorithm

import (
	"testing"
)

func TestSort(t *testing.T) {
	//arr := []int{5, 1, 2, 41, 2, 1}
	//sorts := &InsertSort{5, 1, 2, 41, 2, 1, 100, 99, 1, 2}
	//sorts := &ShellSort{5, 1, 2, 41, 2, 1, 100, 99, 1, 2}
	//sorts := &MergeSort{5, 1, 2, 41, 2, 1, 100, 99, 1, 2}
	sorts := &QuickSort{5, 1, 2, 41, 2, 1, 100, 99, 1, 2}
	sort(sorts)
}

func sort(arr Sort) {
	arr.SortImpl()
}
