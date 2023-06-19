package com.andrii_a.muze.util

object Environment {
    val driverClassName: String = System.getenv("JDBC_DRIVER")
    val databaseUrl: String = System.getenv("DATABASE_URL")
    val baseServerUrl: String = System.getenv("BASE_URL")
    val artworksDirectoryUrl: String = System.getenv("ARTWORKS_DIR")
    val artistsPortraitsDirectoryUrl: String = System.getenv("ARTISTS_PORTRAITS_DIR")
}