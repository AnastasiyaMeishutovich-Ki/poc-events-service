package com.ki.events.service

import com.ki.events.GreeterGrpcKt
import com.ki.events.HelloReply
import com.ki.events.HelloRequest


class EventService: GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest): HelloReply {
        val reply = HelloReply.newBuilder().setMessage("Hello, ${request.name}").build()
        return reply
    }
}
