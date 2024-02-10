package com.tananushka.grpcdemo.client;

import com.tananushka.grpcdemo.config.GrpcServerProperties;
import com.tananushka.grpcdemo.grpc.Ping;
import com.tananushka.grpcdemo.grpc.PingPongServiceGrpc;
import com.tananushka.grpcdemo.grpc.Pong;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PingPongClient {

    private final ManagedChannel channel;

    private final PingPongServiceGrpc.PingPongServiceBlockingStub blockingStub;

    public PingPongClient(GrpcServerProperties grpcServerProperties) {
        log.info("\n\nCreating gRPC client channel to {}:{}\n\n", grpcServerProperties.getAddress(),
              grpcServerProperties.getPort());

        this.channel = ManagedChannelBuilder.forAddress(grpcServerProperties.getAddress(),
              grpcServerProperties.getPort()).usePlaintext().build();
        this.blockingStub = PingPongServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() {
        log.info("Shutting down gRPC client channel");
        channel.shutdown();
    }

    public String ping(String message) {
        log.info("\n\nSending request: message={}\n\n", message);

        Ping request = Ping.newBuilder().setMessage(message).build();

        try {
            Pong response = blockingStub.pingPong(request);
            log.info("\n\nReceived response: message={}\n\n", response.getMessage());
            return response.getMessage();
        } catch (Exception e) {
            log.error("gRPC call failed: {}", e.getMessage(), e);
            throw e;
        }
    }
}