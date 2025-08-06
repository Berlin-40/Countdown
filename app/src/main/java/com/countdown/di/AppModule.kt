package com.countdown.di

import android.content.Context
import androidx.room.Room
import com.countdown.data.AppDatabase
import com.countdown.data.dao.CountdownDao
import com.countdown.data.repository.FakeRepositoryImpl
import com.countdown.data.repository.RepositoryImpl
import com.countdown.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleProvider {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideCountdownDao(appDatabase: AppDatabase): CountdownDao {
        return appDatabase.countdownDao
    }

    @Singleton
    @Provides
    fun provideRepository(countdownDao: CountdownDao): Repository {
        return FakeRepositoryImpl()
    }
}
