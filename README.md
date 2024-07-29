# poc-events-service

To see all grpc endpoints:
grpcurl -plaintext localhost:50051 list

To make a request
grpcurl -plaintext -d '{"name": "World"}' localhost:50051 com.ki.events.Greeter/SayHello
