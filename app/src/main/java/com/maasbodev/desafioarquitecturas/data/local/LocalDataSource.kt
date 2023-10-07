package com.maasbodev.desafioarquitecturas.data.local

import com.maasbodev.desafioarquitecturas.data.entities.Character
import com.maasbodev.desafioarquitecturas.data.local.dao.CharacterDao
import com.maasbodev.desafioarquitecturas.data.local.entities.toCharacter
import com.maasbodev.desafioarquitecturas.data.local.entities.toLocalCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val dao: CharacterDao) {

	val characters: Flow<List<Character>> = dao.getCharacters().map { characters ->
		characters.map { it.toCharacter() }
	}

	suspend fun insertAllCharacters(characters: List<Character>) {
		dao.insertAllCharacters(characters.map { it.toLocalCharacter() })
	}

	suspend fun updateCharacter(character: Character) {
		dao.updateCharacters(character.toLocalCharacter())
	}

	suspend fun count(): Int {
		return dao.count()
	}

}