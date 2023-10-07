package com.maasbodev.desafioarquitecturas.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maasbodev.desafioarquitecturas.data.entities.Character
import com.maasbodev.desafioarquitecturas.data.entities.ReferenceList
import com.maasbodev.desafioarquitecturas.data.entities.Url

@Entity
data class LocalCharacter(
	@PrimaryKey
	val id: Int,
	val title: String,
	val description: String,
	val thumbnail: String,
	val references: List<ReferenceList>,
	val urls: List<Url>,
	val favorite: Boolean = false,
)

fun LocalCharacter.toCharacter(): Character = Character(
	id = id,
	title = title,
	description = description,
	thumbnail = thumbnail,
	references = references,
	urls = urls,
	favorite = favorite,
)

fun Character.toLocalCharacter(): LocalCharacter = LocalCharacter(
	id = id,
	title = title,
	description = description,
	thumbnail = thumbnail,
	references = references,
	urls = urls,
	favorite = favorite,
)