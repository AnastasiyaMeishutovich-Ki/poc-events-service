package com.ki.events

import com.ki.events.service.EventService
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService

class GrpcServer {
    private lateinit var server: Server

    fun start() {
        server = ServerBuilder.forPort(50051)
            .addService(EventService())
            .addService(ProtoReflectionService.newInstance()) // Add reflection service
            .build()
            .start()
        println("gRPC Server started, listening on 50051")

        Runtime.getRuntime().addShutdownHook(Thread {
            println("Shutting down gRPC server...")
            this@GrpcServer.stop()
            println("gRPC server shut down.")
        })
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}
