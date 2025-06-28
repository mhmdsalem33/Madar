package com.salem.madar.data.di

import com.salem.madar.data.repo.GenderRepositoryImpl
import com.salem.madar.data.repo.GetUsersRepositoryImpl
import com.salem.madar.data.repo.UpsertUserRepoImpl
import com.salem.madar.domain.repo.GenderRepository
import com.salem.madar.domain.repo.GetUsersRepository
import com.salem.madar.domain.repo.UpsertUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGenderRepository( genderRepositoryImpl: GenderRepositoryImpl) : GenderRepository = genderRepositoryImpl


    @Singleton
    @Provides
    fun provideUpsertUserRepository(  upsertUserRepoImpl: UpsertUserRepoImpl): UpsertUserRepository {
        return upsertUserRepoImpl
    }

    @Singleton
    @Provides
    fun provideGetUsersRepository( getUsersRepoImpl: GetUsersRepositoryImpl): GetUsersRepository = getUsersRepoImpl


}