package pattern_design

import (
	"fmt"
	"testing"
	"unsafe"
)

func TestPattern(t *testing.T) {
	single := GetSingleton()
	fmt.Println(unsafe.Pointer(single))
	single = GetSingleton()
	fmt.Println(unsafe.Pointer(single))
}
