package com.maasbodev.desafioarquitecturas.data

import com.maasbodev.desafioarquitecturas.BuildConfig
import com.maasbodev.desafioarquitecturas.data.entities.Character
import com.maasbodev.desafioarquitecturas.data.local.LocalDataSource
import com.maasbodev.desafioarquitecturas.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class MarvelRepository(
	private val localDataSource: LocalDataSource,
	private val remoteDataSource: RemoteDataSource
) {
	val characters: Flow<List<Character>> = localDataSource.characters

	suspend fun updateCharacter(character: Character) {
		localDataSource.updateCharacter(character)
	}

	suspend fun requestCharacters() {
		val mpk = BuildConfig.MPK
		val mh = BuildConfig.MH
		val isDbEmpty = localDataSource.count() == 0
		if (isDbEmpty) {
			localDataSource.insertAllCharacters(remoteDataSource.getCharacters(mpk, mh))
		}
	}
}