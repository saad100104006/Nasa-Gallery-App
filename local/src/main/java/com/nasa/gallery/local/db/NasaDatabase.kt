package com.nasa.gallery.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nasa.gallery.local.model.NasaItemLocal

@Database(version = 1, exportSchema = false, entities = [NasaItemLocal::class])
abstract class NasaDatabase : RoomDatabase() {

    abstract fun dao(): NasaDao
}