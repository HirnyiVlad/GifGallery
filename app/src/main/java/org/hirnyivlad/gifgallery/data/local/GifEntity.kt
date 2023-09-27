package org.hirnyivlad.gifgallery.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GifEntity(
    val gifUrl: String,
    @PrimaryKey(autoGenerate = true)
    val gifId: Int
)