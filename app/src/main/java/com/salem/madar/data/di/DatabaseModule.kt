package com.salem.madar.data.di

import android.app.Application
import androidx.room.Room
import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.data.data_source.local.roomDB.database.AppDatabase
import com.salem.madar.data.repo.GetUsersRepositoryImpl
import com.salem.madar.data.repo.UpsertUserRepoImpl
import com.salem.madar.domain.repo.GetUsersRepository
import com.salem.madar.domain.repo.UpsertUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideUsersDatabase( app  : Application ) : AppDatabase =
        Room.databaseBuilder( app , AppDatabase::class.java , "users.db")
            .build()


    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase ): UserDao {
        return database.userDao()
    }

}