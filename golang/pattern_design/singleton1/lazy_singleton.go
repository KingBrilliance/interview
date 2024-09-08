package singleton1

import "sync"

var (
	lazySingleton *mySingleton
	once          = &sync.Once{}
)

// GetLazyInstance 懒汉式 双重校验
func GetLazyInstance() *mySingleton {
	if lazySingleton == nil {
		once.Do(func() {
			lazySingleton = &mySingleton{}
		})
	}
	return lazySingleton
}
