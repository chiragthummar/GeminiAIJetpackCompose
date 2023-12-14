package com.md.geminiaijetpackcompose.core.di

import com.md.geminiaijetpackcompose.data.repository.ChatRepositoryImpl
import com.md.geminiaijetpackcompose.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ) : ChatRepository

}