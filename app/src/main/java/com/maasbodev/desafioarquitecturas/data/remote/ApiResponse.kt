package com.maasbodev.desafioarquitecturas.data.remote

data class ApiResponse<T>(
	val code: Int,
	val status: String,
	val copyright: String,
	val data: ApiData<T>,
	val etag: String,
	val attributionHTML: String,
	val attributionText: String,
)