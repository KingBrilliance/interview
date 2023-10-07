package main

import (
	"context"
	"fmt"
	"google.golang.org/grpc"
	pb "grpc/hello-server/proto"
	"net"
)

type server struct {
	pb.UnimplementedSayHelloServer
}

func (s *server) SayHello(ctx context.Context, in *pb.HelloRequest) (*pb.HelloResponse, error) {
	return &pb.HelloResponse{ResponseMsg: in.RequestName + " 你好呀"}, nil
}

func main() {
	listen, _ := net.Listen("tcp", ":9090")

	newServer := grpc.NewServer()
	pb.RegisterSayHelloServer(newServer, new(server))
	if err := newServer.Serve(listen); err != nil {
		fmt.Println(err)
	}
}
