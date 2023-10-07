package main

import (
	"context"
	"fmt"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	pb "grpc/hello-server/proto"
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
