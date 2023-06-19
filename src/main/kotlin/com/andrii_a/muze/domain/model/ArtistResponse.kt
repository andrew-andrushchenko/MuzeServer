package com.andrii_a.muze.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponse(
    val id: Int,
    val name: String,
    val bornDateString: String?,
    val diedDateString: String?,
    val portraitImageUrl: String,
    val bio: String
)
