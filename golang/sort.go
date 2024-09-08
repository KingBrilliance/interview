package main

import (
	"bufio"
	"fmt"
	"os"
	"reflect"
	"runtime"
	"time"
)

type IntSlice []int

func (s IntSlice) BubbleSort() {
	for i := 0; i < len(s); i++ {
		for j := 0; j < i; j++ {
			if s[i] < s[j] {
				s[i], s[j] = s[j], s[i]
			}
		}
	}
}

func (s IntSlice) SelectSort() {
	for i := 0; i < len(s); i++ {
		index := i
		for j := i + 1; j < len(s); j++ {
			if s[index] > s[j] {
				index = j
			}
		}
		s[i], s[index] = s[index], s[i]
	}
}

func (s IntSlice) InsertSort() {
	for i := 1; i < len(s); i++ {
		index, val := i-1, s[i]
		for index >= 0 && s[index] > val {
			s[index+1] = s[index]
			index--
		}
		s[index+1] = val
	}
}

func (s IntSlice) ShellSort() {
	for h := len(s)>>1 - 1; h > 0; h-- {
		for i := h; i < len(s); i += h {
			index, val := i-h, s[i]
			for index >= 0 && s[index] > val {
				s[index+h] = s[index]
				index -= h
			}
			s[index+h] = val
		}
	}
}

type Rule func(i, j int) bool

func Sort(arr []int, rule Rule) {
	quickSort(arr, 0, len(arr)-1, rule)
}

func quickSort(arr []int, l, r int, rule Rule) {
	if l >= r {
		return
	}
	pivot, i, j := arr[l], l+1, r
	for i <= j {
		for i <= j && rule(i, l) {
			i++
		}
		for i <= j && rule(l, j) {
			j--
		}
		if i >= j {
			break
		}
		arr[i], arr[j] = arr[j], arr[i]
	}
	arr[j], arr[l] = pivot, arr[j]
	if l < j-1 {
		quickSort(arr, l, j-1, rule)
	}
	if j+1 < r {
		quickSort(arr, j+1, r, rule)
	}
}

func (s IntSlice) HeapSort() {
	for i := len(s)>>1 - 1; i >= 0; i-- {
		s.heapify(i, len(s))
	}
	for i := len(s) - 1; i > 0; i-- {
		s[0], s[i] = s[i], s[0]
		s.heapify(0, i)
	}
}

func (s IntSlice) heapify(index, length int) {
	val := s[index]
	for i := index*2 + 1; i < length; i = 2*index + 1 {
		if i+1 < length && s[i+1] > s[i] {
			i++
		}
		if s[i] <= val {
			break
		}
		s[index] = s[i]
		index = i
	}
	s[index] = val
}

func main() {
	//s := IntSlice{2, 3, 1, 3, 71, 8, 3}
	//sort.Slice(s, func(i, j int) bool {
	//	return s[i] < s[j]
	//})
	//s.BubbleSort()
	//s.SelectSort()
	//s.InsertSort()
	//s.ShellSort()
	//s.HeapSort()
	//Sort(s, func(i, j int) bool {
	//	return s[i] < s[j]
	//})
	//fmt.Println(s)
	//TestDefer()
	//name := flag.String("name", "Go语言", "输入你的名字")
	//flag.Parse()
	//fmt.Println(*name, os.Args)
	gomaxprocs := runtime.GOMAXPROCS(1)
	fmt.Println(gomaxprocs)

	TestFunc()
}

func TestDefer() {
	defer func() {
		if r := recover(); r != nil {

		}
	}()
	panic("123")
}

func Input1() string {
	var str string
	fmt.Scanln(&str)
	return str
}

func Input2() string {
	scanner := bufio.NewScanner(os.Stdin)
	if scanner.Scan() {
		return scanner.Text()
	}

	return ""
}

func testChan() {
	for range time.Tick(time.Second) {

	}
}

func TestFunc() {
	// 函数不可以比较
	a := testChan
	b := testChan
	fmt.Println(reflect.DeepEqual(a, b))
}
