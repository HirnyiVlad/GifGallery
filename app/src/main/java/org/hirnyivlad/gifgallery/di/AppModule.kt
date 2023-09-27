package org.hirnyivlad.gifgallery.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.hirnyivlad.gifgallery.data.local.GifDatabase
import org.hirnyivlad.gifgallery.data.local.GifEntity
import org.hirnyivlad.gifgallery.data.remote.GifApi
import org.hirnyivlad.gifgallery.data.remote.gif.GifDto
import org.hirnyivlad.gifgallery.data.remote.gif.GifRemoteMediator
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): GifDatabase {
        return Room.databaseBuilder(
            context,
            GifDatabase::class.java,
            "user.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGifApi(): GifApi {
        return Retrofit.Builder()
            .baseUrl(GifApi.baseGifUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePager(gifDb: GifDatabase, gifApi: GifApi): Pager<Int, GifEntity> {
        return Pager(
            config = PagingConfig(20),
            remoteMediator = GifRemoteMediator(
                gifDb,
                gifApi
            ),
            pagingSourceFactory = {
                gifDb.dao.pagingSource()
            }
        )
    }
}