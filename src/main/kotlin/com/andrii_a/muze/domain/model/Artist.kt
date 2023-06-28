package com.andrii_a.muze.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: Int,
    val name: String,
    val bornDateString: String?,
    val diedDateString: String?,
    val portraitImage: Image,
    val bio: String
)
