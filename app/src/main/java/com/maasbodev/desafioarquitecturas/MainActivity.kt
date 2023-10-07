package com.maasbodev.desafioarquitecturas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.maasbodev.desafioarquitecturas.data.MarvelRepository
import com.maasbodev.desafioarquitecturas.data.local.LocalDataSource
import com.maasbodev.desafioarquitecturas.data.local.MarvelDatabase
import com.maasbodev.desafioarquitecturas.data.remote.RemoteDataSource
import com.maasbodev.desafioarquitecturas.ui.screens.home.Home

class MainActivity : ComponentActivity() {

	private lateinit var db: MarvelDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		db = Room.databaseBuilder(
			applicationContext,
			MarvelDatabase::class.java, "marvel-db"
		).build()

		val repository = MarvelRepository(
			localDataSource = LocalDataSource(db.charactersDao()),
			remoteDataSource = RemoteDataSource(),
		)

		setContent {
			Home(marvelRepository = repository)
		}
	}
}