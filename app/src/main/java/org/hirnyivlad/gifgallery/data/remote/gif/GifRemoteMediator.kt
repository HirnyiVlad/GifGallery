package org.hirnyivlad.gifgallery.data.remote.gif

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import kotlinx.coroutines.delay
import org.hirnyivlad.gifgallery.data.local.GifDatabase
import org.hirnyivlad.gifgallery.data.local.GifEntity
import org.hirnyivlad.gifgallery.data.mapper.toGifEntity
import org.hirnyivlad.gifgallery.data.remote.GifApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GifRemoteMediator(
    private val gifDb: GifDatabase,
    private val gifApi: GifApi
) : RemoteMediator<Int, GifEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.gifId / state.config.pageSize) + 1
                    }
                }
            }
            delay(2000L)
            val gif = gifApi.getGifs(
                page = loadKey,
                pageCount = state.config.pageSize
            )
            gifDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    gifDb.dao.clearAll()
                }
                val gifEntities = gif.data.map { it.images.original.toGifEntity() }
                gifDb.dao.upsertAll(gifEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = gif.data.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}