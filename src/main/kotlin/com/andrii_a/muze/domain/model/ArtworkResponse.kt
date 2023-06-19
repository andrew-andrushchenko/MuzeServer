package com.andrii_a.muze.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ArtworkResponse(
    val id: Int,
    val name: String,
    val year: String?,
    val location: String,
    val imageUrl: String,
    val description: String?,
    val artist: ArtistResponse
)