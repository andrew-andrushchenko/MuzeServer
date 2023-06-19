package com.andrii_a.muze.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import java.io.File

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Welcome to Muze server!")
        }

        staticFiles("/", File("/")) {
            preCompressed(CompressedFileType.GZIP)
        }
    }
}
