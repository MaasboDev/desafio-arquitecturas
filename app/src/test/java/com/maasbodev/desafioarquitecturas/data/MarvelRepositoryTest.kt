package com.maasbodev.desafioarquitecturas.data

import com.maasbodev.desafioarquitecturas.data.entities.Character
import com.maasbodev.desafioarquitecturas.data.local.LocalDataSource
import com.maasbodev.desafioarquitecturas.data.remote.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class MarvelRepositoryTest {

	@Test
	fun `When DB is empty, server is called`() {
		val localDataSource = mock<LocalDataSource>() {
			onBlocking { count() } doReturn 0
		}
		val remoteDataSource = mock<RemoteDataSource>()
		val repository = MarvelRepository(localDataSource, remoteDataSource)

		runBlocking { repository.requestCharacters() }

		verifyBlocking(remoteDataSource) { getCharacters(any(), any()) }
	}

	@Test
	fun `When DB is empty, characters are saved into DB`() {
		val expectedCharacters = listOf(Character(0, "", "", "", emptyList(), emptyList(), false))
		val localDataSource = mock<LocalDataSource>() {
			onBlocking { count() } doReturn 0
		}
		val remoteDataSource = mock<RemoteDataSource>() {
			onBlocking { getCharacters(any(), any()) } doReturn expectedCharacters
		}
		val repository = MarvelRepository(localDataSource, remoteDataSource)

		runBlocking { repository.requestCharacters() }

		verifyBlocking(localDataSource) { insertAllCharacters(expectedCharacters) }
	}

	@Test
	fun `When DB is not empty, remote data source is not called`() {
		val localDataSource = mock<LocalDataSource>() {
			onBlocking { count() } doReturn 1
		}
		val remoteDataSource = mock<RemoteDataSource>()
		val repository = MarvelRepository(localDataSource, remoteDataSource)

		runBlocking { repository.requestCharacters() }

		verifyBlocking(remoteDataSource, times(0)) { getCharacters(any(), any()) }
	}
}