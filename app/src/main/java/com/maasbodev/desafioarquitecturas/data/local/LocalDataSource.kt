package com.maasbodev.desafioarquitecturas.data.local

import com.maasbodev.desafioarquitecturas.data.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val dao: MoviesDao) {

	val movies: Flow<List<Movie>> = dao.getMovies().map { movies ->
		movies.map { it.toMovie() }
	}

	suspend fun insertAll(movies: List<Movie>) {
		dao.insertAll(movies.map { it.toLocalMovie() })
	}

	suspend fun updateMovie(movie: Movie) {
		dao.updateMovie(movie.toLocalMovie())
	}

	suspend fun count(): Int {
		return dao.count()
	}

}