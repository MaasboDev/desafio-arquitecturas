package com.maasbodev.desafioarquitecturas.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

	@GET("characters")
	suspend fun getCharacters(
		@Query("limit") limit: Int,
		@Query("apikey") clau: String,
		@Query("ts") ts: String,
		@Query("hash") hash: String,
	): ApiResponse<ApiCharacter>
}