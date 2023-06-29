package com.andrii_a.muze.util

enum class ImageType {
    ARTIST_PORTRAIT,
    ARTWORK
}

fun constructUrlForImage(filename: String, imageType: ImageType): String =
    when (imageType) {
        ImageType.ARTIST_PORTRAIT -> "${Environment.baseServerUrl}${Environment.artistsPortraitsDir}$filename"
        ImageType.ARTWORK -> "${Environment.baseServerUrl}${Environment.artworksDir}$filename"
    }