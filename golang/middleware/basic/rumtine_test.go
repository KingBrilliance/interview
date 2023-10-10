package basic

import (
	"context"
	"fmt"
	"runtime"
	"testing"
	"time"
)

func TestRuntime(t *testing.T) {
	ctx := context.Background()
	cancel, cancelFunc := context.WithCancel(ctx)
	go func() {
		for {
			time.Sleep(time.Second)
			fmt.Println(getGoroutineID())
			exit := func() {
				ids := getGoroutineID()
				fmt.Println(ids)
				runtime.Goexit()
			}

			go func() {
				<-cancel.Done()
				exit()
			}()
		}
	}()

	<-time.After(time.Second * 2)
	cancelFunc()

	time.Sleep(time.Second * 3)
	fmt.Println("exit")
	runtime.GC()
}

func getGoroutineID() int64 {
	var buf [64]byte
	n := runtime.Stack(buf[:], false)
	var id int64
	fmt.Sscanf(string(buf[:n]), "goroutine %d", &id)
	return id
}
