package com.maasbodev.desafioarquitecturas.data.remote

data class ApiThumbnail(
	val extension: String,
	val path: String
)

fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")