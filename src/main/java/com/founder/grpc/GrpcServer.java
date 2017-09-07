package com.founder.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    private Server server;

    private void start() throws IOException {
        int port = 8899;
        server = ServerBuilder.forPort(port)
                .addService(new StudentServiceImpl())
                .build()
                .start();

        System.out.println("Server started!");

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            GrpcServer.this.stop();
            System.err.println("*** server shut down");
        }));

        System.out.println("执行到这里！");

    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {

        GrpcServer grpcServer = new GrpcServer();
        grpcServer.start();
        grpcServer.awaitTermination();
    }



}
