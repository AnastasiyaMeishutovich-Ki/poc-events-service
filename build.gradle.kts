import com.google.protobuf.gradle.id

plugins {
    alias(deps.plugins.kotlin)
    alias(deps.plugins.ktor)
    alias(deps.plugins.protobuf)
}

group = "com.ki.events"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.ki.events.ApplicationKt")
}

dependencies {
    implementation(deps.bundles.grpc)
    implementation(deps.bundles.ktor)
    implementation(deps.bundles.logging)
    testImplementation(deps.bundles.testing)
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = deps.plugins.protoc.get().toString()
    }

    plugins {
        id("grpc") {
            setArtifact(deps.plugins.grpcjava.get().toString())
        }
        id("grpckt") {
            setArtifact(deps.plugins.grpckotlin.get().toString())
        }
    }

    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
    }
}
