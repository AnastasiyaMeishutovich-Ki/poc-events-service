package com.ki.events

import io.ktor.server.routing.Routing
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get


fun main() {
    // Start the gRPC server
    val grpcServer = GrpcServer()
    grpcServer.start()

    // Start the Ktor server
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        install(Routing) {
            get("/") {
                call.respondText("Hello, World!", status = HttpStatusCode.OK)
            }
        }
    }.start(wait = true)

    grpcServer.blockUntilShutdown()
}