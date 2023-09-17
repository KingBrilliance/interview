package pattern_design

import "sync"

type singleton struct{}

var defaultSingleton *singleton

var once sync.Once

func GetSingleton() *singleton {
	once.Do(func() {
		if defaultSingleton == nil {
			defaultSingleton = &singleton{}
		}
	})
	return defaultSingleton
}
