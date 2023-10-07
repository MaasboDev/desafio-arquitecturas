package com.maasbodev.desafioarquitecturas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.maasbodev.desafioarquitecturas.data.local.entities.LocalCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

	@Query("SELECT * FROM LocalCharacter")
	fun getCharacters(): Flow<List<LocalCharacter>>

	@Insert
	suspend fun insertAllCharacters(localCharacters: List<LocalCharacter>)

	@Update
	suspend fun updateCharacters(localCharacter: LocalCharacter)

	@Query("SELECT COUNT(*) FROM LocalCharacter")
	suspend fun count(): Int
}