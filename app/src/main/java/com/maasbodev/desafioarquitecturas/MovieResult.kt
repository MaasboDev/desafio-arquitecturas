package com.maasbodev.desafioarquitecturas

data class MovieResult(
	val page: Int,
	val results: List<ServerMovie>,
	val total_pages: Int,
	val total_results: Int
)