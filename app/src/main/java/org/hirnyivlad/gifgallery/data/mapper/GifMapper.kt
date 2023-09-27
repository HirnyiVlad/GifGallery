package org.hirnyivlad.gifgallery.data.mapper

import org.hirnyivlad.gifgallery.data.domain.Gif
import org.hirnyivlad.gifgallery.data.local.GifEntity
import org.hirnyivlad.gifgallery.data.remote.gif.GifDto
fun GifDto.toGifEntity():GifEntity{
    return GifEntity(
        gifUrl = url,
        gifId = id
    )
}

fun GifEntity.toGif() : Gif {
    return Gif(
        gifUrl = gifUrl,
        gifId = gifId
    )
}