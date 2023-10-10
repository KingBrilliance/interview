package main

import (
	"context"
	"fmt"
	clientv3 "go.etcd.io/etcd/client/v3"
	"go.etcd.io/etcd/client/v3/naming/resolver"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	pb "grpc/hello-server/proto"
	"time"
)

func main() {
	conn, err := grpc.Dial("127.0.0.1:9090", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		fmt.Println("")
		return
	}

	defer conn.Close()
	client := pb.NewSayHelloClient(conn)

	hello, err := client.SayHello(context.Background(), &pb.HelloRequest{RequestName: "大王灿"})
	if err != nil {
		return
	}

	fmt.Println(hello.ResponseMsg)
}

// EtcdConnect ETCD实现服务发现
func EtcdConnect() {
	cli, err := clientv3.NewFromURL("http://localhost:2379")
	if err != nil {
		// 处理连接错误
	}
	defer cli.Close()

	builder, err := resolver.NewBuilder(cli)
	if err != nil {
		return
	}

	ctx, cancel := context.WithTimeout(context.Background(), time.Second*2)
	conn, err := grpc.Dial("etcd:///service/go-demo", // 在etcd中注册的服务名称
		grpc.WithResolvers(builder),
		grpc.WithInsecure(), grpc.WithTimeout(time.Second))
	if err != nil {
		return
	}

	client := pb.NewSayHelloClient(conn)
	defer cancel()
	resp, err := client.SayHello(ctx, &pb.HelloRequest{RequestName: "lily"})
	if err != nil {
		return
	}

	fmt.Println(resp.ResponseMsg)
}

//
func ConsoleConnect() {

}
