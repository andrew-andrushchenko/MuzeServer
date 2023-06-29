package com.andrii_a.muze.util

import java.nio.file.Paths

object Environment {
    private val currentWorkingDir: String = Paths.get(".").toAbsolutePath().normalize().toString()
    private val defaultArtworksDir: String = "$currentWorkingDir/data/artworks/"
    private val defaultArtistsPortraitsDir: String = "$currentWorkingDir/data/artists_portraits/"

    val baseServerUrl: String = System.getenv("BASE_URL")
    val driverClassName: String = System.getenv("JDBC_DRIVER")
    val databaseUrl: String = System.getenv("DATABASE_URL")

    val artworksDir: String = System.getenv("ARTWORKS_DIR") ?: defaultArtworksDir
    val artistsPortraitsDir: String = System.getenv("ARTISTS_PORTRAITS_DIR") ?: defaultArtistsPortraitsDir
}