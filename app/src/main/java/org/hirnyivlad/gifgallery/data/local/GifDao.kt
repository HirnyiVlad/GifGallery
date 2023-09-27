package org.hirnyivlad.gifgallery.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface GifDao {
    @Upsert
    suspend fun upsertAll(gifs: List<GifEntity>)

    @Query("SELECT * FROM gifentity")
    fun pagingSource(): PagingSource<Int, GifEntity>

    @Query("Delete FROM gifentity")
    fun clearAll()
}