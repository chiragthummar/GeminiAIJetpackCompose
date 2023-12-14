package com.md.geminiaijetpackcompose.core.di

import android.app.Application
import androidx.room.Room
import com.md.geminiaijetpackcompose.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.md.geminiaijetpackcompose.data.local.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChatModel() : GenerativeModel {
            return GenerativeModel(
                modelName = "gemini-pro",
                apiKey = BuildConfig.apiKey
            ).apply {
                startChat()
            }
    }

    @Provides
    @Singleton
    fun getChatDatabse(app: Application) : ChatDatabase {
        return Room.databaseBuilder(
            app,
            ChatDatabase::class.java,
            "chatdb"
        ).build()
    }

}