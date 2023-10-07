package com.maasbodev.desafioarquitecturas.data.remote

data class ApiReferenceList(
	val available: Int,
	val collectionURI: String,
	val items: List<ApiReference>?,
	val returned: Int
)