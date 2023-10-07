package com.maasbodev.desafioarquitecturas.data.remote

import com.maasbodev.desafioarquitecturas.data.entities.Character
import com.maasbodev.desafioarquitecturas.data.generateHash
import java.util.Date
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

	suspend fun getCharacters(clau: String, amagat: String): List<Character> {
		val ts = Date().time
		val tapat = generateHash(ts, amagat, clau)
		return Retrofit.Builder()
			.baseUrl("https://gateway.marvel.com:443/v1/public/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(MarvelService::class.java)
			.getCharacters(100, clau, ts.toString(), tapat)
			.data
			.results
			.map { it.asCharacter() }
	}
}