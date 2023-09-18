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

func TestPrototype_Clone(t *testing.T) {
	prototype := &Prototype{Data: "老李"}
	prototype = prototype.Clone().(*Prototype)
}
