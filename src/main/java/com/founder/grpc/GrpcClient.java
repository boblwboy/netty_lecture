package com.founder.grpc;

import com.founder.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;


public class GrpcClient {


    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

//        MyResponse myResponse = blockingStub.getRealnameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
//        System.out.println(myResponse.getRealname());
//
//        System.out.println("-----------------------");
//
//        Iterator<StudentResponse> studentResponseIterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
//
//        while (studentResponseIterator.hasNext()) {
//            StudentResponse studentResponse = studentResponseIterator.next();
//            System.out.println(studentResponse.getName() + "," +studentResponse.getAge() + "," + studentResponse.getCity());
//        }
//
//        System.out.println("-----------------------");

//        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
//            @Override
//            public void onNext(StudentResponseList value) {
//                value.getStudentResponseList().forEach(studentResponse -> {
//                    System.out.println(studentResponse.getName() + "," + studentResponse.getAge() + "," + studentResponse.getCity());
//                    System.out.println("******");
//                });
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("completed!");
//            }
//        };
//
//        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWapperByAge(studentResponseListStreamObserver);
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
//
//        studentRequestStreamObserver.onCompleted();
//
//        System.out.println("-----------------------");

        StreamObserver<StreamRquest> streamRquestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed!");
            }
        });

        for (int i = 0 ; i<10;i++) {
            streamRquestStreamObserver.onNext(StreamRquest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        streamRquestStreamObserver.onCompleted();


        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
