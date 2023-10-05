package com.maasbodev.desafioarquitecturas

import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {

	@GET("characters?limit=100&apikey=dee033d28552710396e7d9b1d3b44efb")
	suspend fun getCharacters(
		@Path("limit") limit: Int,
		@Path("api_key") apiKey: String
	): MovieResult
}