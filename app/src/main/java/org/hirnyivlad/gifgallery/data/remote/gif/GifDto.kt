package org.hirnyivlad.gifgallery.data.remote.gif

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("data") val data : List<GifObject>
)

data class GifObject(
    @SerializedName("images") val images: GifData
)

data class GifData(
    @SerializedName("original") val original: GifDto
)

data class GifDto(
    @SerializedName("url")
    val url: String,
    @SerializedName("id")
    val id: Int
)