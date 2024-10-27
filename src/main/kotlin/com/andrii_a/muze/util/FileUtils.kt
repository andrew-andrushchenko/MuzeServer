package com.andrii_a.muze.util

import io.ktor.http.content.*
import io.ktor.server.http.content.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.readByteArray
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

@Throws(UploadedFileNotAnImageException::class)
suspend fun PartData.FileItem.trySave(destinationPath: String): String {
    val bytes = provider().readRemaining().readByteArray()

    withContext(Dispatchers.IO) {
        if (ImageIO.read(bytes.inputStream()) == null) {
            throw UploadedFileNotAnImageException()
        }
    }

    val fileExtension = originalFileName?.takeLastWhile { it != '.' }
    val fileName = "${UUID.randomUUID()}.$fileExtension"

    val folder = File(destinationPath)
    folder.mkdir()

    val pathToSave = "$destinationPath$fileName"
    File(pathToSave).writeBytes(bytes)

    return fileName
}

fun File.asImage(): BufferedImage = ImageIO.read(this)