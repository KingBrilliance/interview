package sort_algorithm

import (
	"fmt"
	"sync"
)

type Person struct {
	Age int
}

// 初始化pool
var personPool = sync.Pool{
	New: func() interface{} {
		return new(Person)
	},
}

func main() {
	// 获取一个实例
	newPerson := personPool.Get().(*Person)
	// 回收对象 以备其他协程使用
	defer personPool.Put(newPerson)

	newPerson.Age = 25
}

type ShellSort []int

func (s ShellSort) SortImpl() {
	for h := len(s) >> 1; h > 0; h >>= 1 {
		for i := h; i < len(s); i += h {
			index, tmp := i-h, s[i]
			for index >= 0 && tmp < s[index] {
				s[index+h] = s[index]
				index -= h
			}
			s[index+h] = tmp
		}
	}
	fmt.Println(s)
}
