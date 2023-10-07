package com.maasbodev.desafioarquitecturas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maasbodev.desafioarquitecturas.data.local.dao.CharacterDao
import com.maasbodev.desafioarquitecturas.data.local.entities.LocalCharacter

@Database(entities = [LocalCharacter::class], version = 1)
@TypeConverters(Converters::class)
abstract class MarvelDatabase : RoomDatabase() {
	abstract fun charactersDao(): CharacterDao
}