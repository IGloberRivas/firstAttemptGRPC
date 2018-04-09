package grpc.ft.rhcp.service;

import com.example.server.GreetingServiceGrpc;
import com.example.server.HelloRequest;
import com.example.server.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String name = request.getName();
        String firstHobbie = request.getHobbies(0);
        String firstTrick = request.getBagOfTricksOrDefault("Trick1", "magic!");

        HelloResponse response1 = HelloResponse
                                    .newBuilder()
                                    .setGreeting("Hello " + name)
                                    .addFirstHobbie(firstHobbie)
                                    .putBagOfTricks("Trick1", firstTrick)
                                    .build();

        responseObserver.onNext(response1);
        responseObserver.onCompleted();
    }
}
