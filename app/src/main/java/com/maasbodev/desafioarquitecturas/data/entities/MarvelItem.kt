package com.maasbodev.desafioarquitecturas.data.entities

interface MarvelItem {
	val id: Int
	val title: String
	val description: String
	val thumbnail: String
	val references: List<ReferenceList>
	val urls: List<Url>
	val favorite: Boolean
}