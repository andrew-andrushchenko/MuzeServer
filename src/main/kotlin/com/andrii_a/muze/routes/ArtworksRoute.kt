package com.andrii_a.muze.routes

import com.andrii_a.muze.domain.model.Artwork
import com.andrii_a.muze.domain.model.Image
import com.andrii_a.muze.domain.repository.ArtworksRepository
import com.andrii_a.muze.util.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.artworksRoute(repository: ArtworksRepository) {
    route("artworks") {
        get {
            val page = call.request.queryParameters["page"]?.toInt() ?: DEFAULT_INITIAL_PAGE
            val perPage = call.request.queryParameters["per_page"]?.toInt() ?: DEFAULT_PAGE_SIZE

            call.respond(
                message = repository.getArtworks(page, perPage),
                status = HttpStatusCode.OK
            )
        }

        get("by-artist/{id?}") {
            val artistId = call.parameters["id"]?.toInt() ?: return@get call.respondText(
                text = "Missing artist id",
                status = HttpStatusCode.BadRequest
            )

            val page = call.request.queryParameters["page"]?.toInt() ?: DEFAULT_INITIAL_PAGE
            val perPage = call.request.queryParameters["per_page"]?.toInt() ?: DEFAULT_PAGE_SIZE

            call.respond(
                message = repository.getArtworksByArtist(artistId, page, perPage),
                status = HttpStatusCode.OK
            )
        }

        get("{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText(
                text = "Missing artwork id",
                status = HttpStatusCode.BadRequest
            )

            repository.getArtwork(id)?.let { painting ->
                call.respond(
                    message = painting,
                    status = HttpStatusCode.OK
                )
            } ?: call.respond(
                message = "No artwork with id $id",
                status = HttpStatusCode.NotFound
            )
        }

        get("search") {
            val query = call.request.queryParameters["query"].orEmpty()
            val page = call.request.queryParameters["page"]?.toInt() ?: DEFAULT_INITIAL_PAGE
            val perPage = call.request.queryParameters["per_page"]?.toInt() ?: DEFAULT_PAGE_SIZE

            if (query.isNotEmpty()) {
                call.respond(
                    message = repository.searchArtworks(query, page, perPage),
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(
                    message = emptyList<Artwork>(),
                    status = HttpStatusCode.OK
                )
            }
        }

        post("add") {
            val multipart = call.receiveMultipart()

            var uploadedImageFileName = ""
            var name = ""
            var year = ""
            var location = ""
            var description = ""
            var artistId = -1

            try {
                multipart.forEachPart { partData ->
                    when (partData) {
                        is PartData.FormItem -> {
                            when (partData.name) {
                                "name" -> name = partData.value
                                "year" -> year = partData.value
                                "location" -> location = partData.value
                                "description" -> description = partData.value
                                "artist_id" -> artistId = partData.value.toInt()
                            }
                        }

                        is PartData.FileItem -> {
                            uploadedImageFileName = partData.trySave(Environment.artworksDir)
                        }

                        else -> Unit
                    }
                }

                val imageUrl = "${Environment.artworksDir}$uploadedImageFileName"
                val (width, height) = File(imageUrl).asImage().resolution

                repository.addArtwork(
                    name = name,
                    year = year,
                    location = location,
                    image = Image(
                        width = width,
                        height = height,
                        url = uploadedImageFileName
                    ),
                    description = description,
                    artistId = artistId
                )

                call.respond(
                    message = imageUrl,
                    status = HttpStatusCode.OK
                )

            } catch (ex: Exception) {
                when (ex) {
                    is UploadedFileNotAnImageException -> {
                        call.respond(
                            message = ex.message.orEmpty(),
                            status = HttpStatusCode.BadRequest
                        )
                    }

                    else -> {
                        val imageUrl = "${Environment.artworksDir}$uploadedImageFileName"
                        File(imageUrl).delete()

                        call.respond(
                            message = "Unable to receive data. Try again later",
                            status = HttpStatusCode.InternalServerError
                        )
                    }
                }
            }
        }

        delete {
            val isDeleted = repository.deleteAll()

            if (isDeleted) {
                call.respond(
                    message = "All artworks successfully deleted!",
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(
                    message = "Unable to delete artworks! Try again later.",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }

        delete("{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respondText(
                text = "Missing artwork id",
                status = HttpStatusCode.BadRequest
            )

            val isDeleted = repository.deleteById(id)

            if (isDeleted) {
                call.respond(
                    message = "Artwork with id $id successfully deleted!",
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(
                    message = "Unable to delete artwork! Try again later.",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }
    }
}
