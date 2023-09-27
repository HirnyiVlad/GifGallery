package org.hirnyivlad.gifgallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.map
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import org.hirnyivlad.gifgallery.data.local.GifEntity
import org.hirnyivlad.gifgallery.data.mapper.toGif
import org.hirnyivlad.gifgallery.data.mapper.toGifEntity
import org.hirnyivlad.gifgallery.data.remote.gif.GifDto
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    pager: Pager<Int, GifEntity>
) : ViewModel() {
    val gifPagerFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map {
                it.toGif()
            }
        }.cachedIn(viewModelScope)
}