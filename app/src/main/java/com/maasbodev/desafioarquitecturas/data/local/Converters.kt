package com.maasbodev.desafioarquitecturas.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maasbodev.desafioarquitecturas.data.entities.ReferenceList
import com.maasbodev.desafioarquitecturas.data.entities.Url

class Converters {
	@TypeConverter
	fun fromReferenceList(value: String): List<ReferenceList> {
		val listType = object : TypeToken<List<ReferenceList>>() {}.type
		return Gson().fromJson(value, listType)
	}

	@TypeConverter
	fun toReferenceList(value: List<ReferenceList>): String {
		return Gson().toJson(value)
	}

	@TypeConverter
	fun fromUrl(value: String): List<Url> {
		val listType = object : TypeToken<List<Url>>() {}.type
		return Gson().fromJson(value, listType)
	}

	@TypeConverter
	fun toUrl(value: List<Url>): String {
		return Gson().toJson(value)
	}
}