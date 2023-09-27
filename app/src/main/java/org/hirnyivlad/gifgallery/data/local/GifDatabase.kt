package org.hirnyivlad.gifgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GifEntity::class],
    version = 1
)
abstract class GifDatabase : RoomDatabase() {
    abstract  val dao: GifDao
}