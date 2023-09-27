package org.hirnyivlad.gifgallery.data.remote

import org.hirnyivlad.gifgallery.data.remote.gif.DataResult
import org.hirnyivlad.gifgallery.data.remote.gif.GifObject
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("gifs/trending?api_key=4QuJO1QAowfV9SK3MystneT8y9LaHJ1z")
    suspend fun getGifs(
        @Query("page") page: Int,
        @Query("page_count") pageCount: Int
    ): DataResult

    companion object {
        const val baseGifUrl = "https://api.giphy.com/v1/"
    }
}