package grpc.ft.rhcp.client;

import com.example.server.GreetingServiceGrpc;
import com.example.server.HelloRequest;
import com.example.server.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyGrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9080)
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        HelloResponse response = stub.greeting(HelloRequest.newBuilder()
                .setName("Ivan")
                .putBagOfTricks("Trick1", "magic!")
                .addHobbies("soccer")
                .build());

        System.out.print(response);
    }
}
