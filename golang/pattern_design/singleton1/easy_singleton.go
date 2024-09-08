package singleton1

var single *mySingleton = &mySingleton{}

type mySingleton struct{}

type A struct{}

// GetMySingleton 饿汉式
func GetMySingleton() *mySingleton {
	a := []interface{}{""}
	ints(a...)
	a = append(a, A{})
	return single
}

func ints(agrs ...interface{}) {

}
