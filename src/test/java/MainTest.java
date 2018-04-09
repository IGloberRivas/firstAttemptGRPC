import com.example.server.GreetingServiceGrpc;
import com.example.server.HelloRequest;
import com.example.server.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {
    @Test
    public void AssertName(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9080)
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        HelloResponse response = stub.greeting(HelloRequest.newBuilder()
                .setName("Ivan")
                .addHobbies("play the drums")
                .putBagOfTricks("Trick1", "Morsmordre")
                .build());

        System.out.print(response);

        Assert.assertTrue(response.getGreeting().contains("Ivan"));
        Assert.assertTrue(response.getFirstHobbie(0).contains("play the drums"));
        Assert.assertTrue(response.getBagOfTricksMap().containsValue("Morsmordre"));
    }
}
