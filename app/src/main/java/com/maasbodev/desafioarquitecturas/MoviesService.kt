package com.maasbodev.desafioarquitecturas

import retrofit2.http.GET

interface MoviesService {

	@GET("discover/movie?api_key=246fb4b40c54eae189d373d21ce2e3c6")
	suspend fun getMovies(): MovieResult
}