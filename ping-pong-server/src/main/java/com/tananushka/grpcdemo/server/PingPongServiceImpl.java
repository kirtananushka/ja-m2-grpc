package com.tananushka.grpcdemo.server;

import com.tananushka.grpcdemo.grpc.Ping;
import com.tananushka.grpcdemo.grpc.PingPongServiceGrpc;
import com.tananushka.grpcdemo.grpc.Pong;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {

    @Override
    public void pingPong(Ping request, StreamObserver<Pong> responseObserver) {
        try {
            log.info("\n\nReceived gRPC request: message={}\n\n", request.getMessage());
            
            Pong response = Pong.newBuilder().setMessage("Pong").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("\n\ngRPC response sent successfully\n\n");
        } catch (Exception e) {
            log.error("Error occurred during gRPC request handling: {}", e.getMessage(), e);
            responseObserver.onError(e);
        }
    }
}
