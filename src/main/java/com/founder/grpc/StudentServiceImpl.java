package com.founder.grpc;

import com.founder.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealnameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息："+request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        System.out.println("接收到客户端消息："+request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(20).setCity("北京").build());

        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWapperByAge(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("接收到客户端消息：" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("李四").setAge(20).setCity("北京").build();

                StudentResponseList studentResponseList = StudentResponseList.newBuilder().addStudentResponse(studentResponse).addStudentResponse(studentResponse2).build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRquest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRquest>() {
            @Override
            public void onNext(StreamRquest value) {
                System.out.println("接收到客户端消息：" + value.getRequestInfo());

                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
