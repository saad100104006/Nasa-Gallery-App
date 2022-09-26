package com.nasa.gallery.local.db

import androidx.room.*
import com.nasa.gallery.local.model.NasaItemLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(nasaItemLocal: NasaItemLocal)

    @Query("SELECT * FROM nasa_table")
    fun getAllBookmarks(): Flow<List<NasaItemLocal>>

    @Delete
    suspend fun removeBookmark(nasaItemLocal: NasaItemLocal)
}