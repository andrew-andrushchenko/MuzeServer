package com.andrii_a.muze.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val width: Int,
    val height: Int,
    val url: String
)