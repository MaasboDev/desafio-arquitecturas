package com.maasbodev.desafioarquitecturas.data.entities

data class Event(
	override val id: Int,
	override val title: String,
	override val description: String,
	override val thumbnail: String,
	override val references: List<ReferenceList>,
	override val urls: List<Url>,
	override val favorite: Boolean,
) : MarvelItem