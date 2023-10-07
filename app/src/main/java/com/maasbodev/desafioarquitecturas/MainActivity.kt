package com.maasbodev.desafioarquitecturas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.maasbodev.desafioarquitecturas.data.MoviesRepository
import com.maasbodev.desafioarquitecturas.data.local.LocalDataSource
import com.maasbodev.desafioarquitecturas.data.local.MoviesDatabase
import com.maasbodev.desafioarquitecturas.data.remote.RemoteDataSource
import com.maasbodev.desafioarquitecturas.ui.screens.home.Home

class MainActivity : ComponentActivity() {

	private lateinit var db: MoviesDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		db = Room.databaseBuilder(
			applicationContext,
			MoviesDatabase::class.java, "movies-db"
		).build()

		val repository = MoviesRepository(
			localDataSource = LocalDataSource(db.moviesDao()),
			remoteDataSource = RemoteDataSource(),
		)

		setContent {
			Home(moviesRepository = repository)
		}
	}
}