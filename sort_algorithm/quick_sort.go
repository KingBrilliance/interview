package sort_algorithm

import (
	"fmt"
)

type QuickSort []int

func (q *QuickSort) SortImpl() {
	//*q = q.quickSort(*q, 0, len(*q)-1)
	q.QuickSort()
	fmt.Println(*q)
}

func (q *QuickSort) quickSort(arr QuickSort, l, r int) QuickSort {
	if l < r {
		mid := q.partition(arr, l, r)
		arr = q.quickSort(arr, l, mid-1)
		arr = q.quickSort(arr, mid+1, r)
	}
	return arr
}

func (*QuickSort) partition(arr []int, l, r int) int {
	pivot, i, j := arr[l], l+1, r
	for i <= j {
		for i <= j && arr[i] <= pivot {
			i++
		}
		for j >= i && arr[j] >= pivot {
			j--
		}
		if i >= j {
			break
		}
		arr[i], arr[j] = arr[j], arr[i]
	}
	arr[l], arr[j] = arr[j], pivot
	return j
}

// QuickSort 非递归版本
func (q *QuickSort) QuickSort() {
	stack := [][]int{{0, len(*q) - 1}}
	for length := len(stack); length > 0; length = len(stack) {
		l, r := stack[length-1][0], stack[length-1][1]
		stack = stack[:length-1]
		if l < r {
			mid := q.partition1(*q, l, r)
			if mid-1 > l {
				stack = append(stack, []int{l, mid - 1})
			}
			if mid+1 < r {
				stack = append(stack, []int{mid + 1, r})
			}
		}
	}
}

func (q *QuickSort) partition1(arr []int, l, r int) int {
	pivotIndex := l
	pivotValue := arr[r]

	for i := l; i < r; i++ {
		if arr[i] < pivotValue {
			arr[i], arr[pivotIndex] = arr[pivotIndex], arr[i]
			pivotIndex++
		}
	}

	arr[pivotIndex], arr[r] = arr[r], arr[pivotIndex]

	return pivotIndex
}
