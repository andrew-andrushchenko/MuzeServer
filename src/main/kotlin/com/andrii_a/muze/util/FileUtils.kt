package com.andrii_a.muze.util

import io.ktor.http.content.*
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

@Throws(UploadedFileNotAnImageException::class)
fun PartData.FileItem.trySave(destinationPath: String): String {
    val fileBytes = streamProvider().readBytes()

    if (ImageIO.read(fileBytes.inputStream()) == null) {
        throw UploadedFileNotAnImageException()
    }

    val fileExtension = originalFileName?.takeLastWhile { it != '.' }
    val fileName = "${UUID.randomUUID()}.$fileExtension"

    val folder = File(destinationPath)
    folder.mkdir()

    val savedPath = "$destinationPath$fileName"
    File(savedPath).writeBytes(fileBytes)

    return savedPath
}

fun File.asImage(): BufferedImage = ImageIO.read(this)