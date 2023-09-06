package sort_algorithm

import "fmt"

type HeapSort []int

func (h HeapSort) SortImpl() {
	n := len(h)
	for i := n/2 - 1; i >= 0; i-- {
		h.heapify(h, i, n)
	}
	for i := n - 1; i > 0; i-- {
		h[i], h[0] = h[0], h[i]
		h.heapify(h, 0, i)
	}
	fmt.Println(h)
}

func (h HeapSort) heapify(arr HeapSort, l, n int) {
	tmp := arr[l]
	for i := 2*l + 1; i < n; i = 2*l + 1 {
		if i+1 < n && arr[i] < arr[i+1] {
			i++
		}
		if arr[i] <= tmp {
			break
		}
		arr[l] = arr[i]
		l = i
	}

	arr[l] = tmp
}
