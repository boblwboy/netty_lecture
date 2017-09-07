package com.founder.grpc;

import com.founder.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealnameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("���յ��ͻ�����Ϣ��"+request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("����").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        System.out.println("���յ��ͻ�����Ϣ��"+request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("����").setAge(20).setCity("����").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����").setAge(20).setCity("����").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����").setAge(20).setCity("����").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����").setAge(20).setCity("����").build());

        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWapperByAge(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("���յ��ͻ�����Ϣ��" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("����").setAge(20).setCity("����").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("����").setAge(20).setCity("����").build();

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
                System.out.println("���յ��ͻ�����Ϣ��" + value.getRequestInfo());

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
