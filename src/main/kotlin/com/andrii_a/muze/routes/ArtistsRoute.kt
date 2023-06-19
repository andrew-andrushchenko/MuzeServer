package com.andrii_a.muze.routes

import com.andrii_a.muze.domain.model.ArtistResponse
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.util.DEFAULT_INITIAL_PAGE
import com.andrii_a.muze.util.DEFAULT_PAGE_SIZE
import com.andrii_a.muze.util.Environment
import com.andrii_a.muze.util.save
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.artistsRoute(repository: ArtistsRepository) {
    route("artists") {
        get {
            val page = call.request.queryParameters["page"]?.toInt() ?: DEFAULT_INITIAL_PAGE
            val perPage = call.request.queryParameters["per_page"]?.toInt() ?: DEFAULT_PAGE_SIZE

            call.respond(
                message = repository.getAllArtists(page, perPage),
                status = HttpStatusCode.OK
            )
        }

        get("{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText(
                text = "Missing artist id",
                status = HttpStatusCode.BadRequest
            )

            repository.getArtist(id)?.let { author ->
                call.respond(
                    message = author,
                    status = HttpStatusCode.OK
                )
            } ?: call.respond(
                message = "No artist with id $id",
                status = HttpStatusCode.NotFound
            )
        }

        get("search") {
            val query = call.request.queryParameters["query"].orEmpty()
            val page = call.request.queryParameters["page"]?.toInt() ?: DEFAULT_INITIAL_PAGE
            val perPage = call.request.queryParameters["per_page"]?.toInt() ?: DEFAULT_PAGE_SIZE

            if (query.isNotEmpty()) {
                call.respond(
                    message = repository.searchArtists(query, page, perPage),
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(
                    message = emptyList<ArtistResponse>(),
                    status = HttpStatusCode.OK
                )
            }
        }

        post("add") {
            val multipart = call.receiveMultipart()
            var fileName: String? = null

            var name = ""
            var bornDateString = ""
            var diedDateString = ""
            var bio = ""

            try {
                multipart.forEachPart { partData ->
                    when (partData) {
                        is PartData.FormItem -> {
                            when (partData.name) {
                                "name" -> name = partData.value
                                "born_date_string" -> bornDateString = partData.value
                                "died_date_string" -> diedDateString = partData.value
                                "bio" -> bio = partData.value
                            }
                        }

                        is PartData.FileItem -> {
                            fileName = partData.save(Environment.artistsPortraitsDirectoryUrl)
                        }

                        else -> Unit
                    }
                }

                val portraitImageUrl = "${Environment.baseServerUrl}${Environment.artistsPortraitsDirectoryUrl}$fileName"

                repository.addArtist(
                    name = name,
                    bornDateString = bornDateString,
                    diedDateString = diedDateString,
                    portraitImageUrl = portraitImageUrl,
                    bio = bio
                )

                call.respond(HttpStatusCode.OK, portraitImageUrl)
            } catch (ex: Exception) {
                File("/$fileName").delete()
                call.respond(HttpStatusCode.InternalServerError, "Unable to receive data. Try again later")
            }
        }

        delete {
            val isDeleted = repository.deleteAll()

            if (isDeleted) {
                call.respond(
                    message = "All artists successfully deleted!",
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(
                    message = "Unable to delete artists! Try again later.",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }

        delete("{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respondText(
                text = "Missing artist id",
                status = HttpStatusCode.BadRequest
            )

            val isDeleted = repository.deleteById(id)

            if (isDeleted) {
                call.respond(
                    message = "Artist with id $id successfully deleted!",
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(
                    message = "Unable to delete artist! Try again later.",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }

    }
}