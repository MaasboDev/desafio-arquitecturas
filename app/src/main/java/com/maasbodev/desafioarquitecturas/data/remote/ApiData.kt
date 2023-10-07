package com.maasbodev.desafioarquitecturas.data.remote

data class ApiData<T>(
	val offset: Int,
	val limit: Int,
	val total: Int,
	val count: Int,
	val results: List<T>,
)