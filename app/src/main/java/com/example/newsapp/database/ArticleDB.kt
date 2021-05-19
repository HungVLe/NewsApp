package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.dao.ArticleDao
import com.example.newsapp.models.Article
import com.example.newsapp.util.Converter


@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDB : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDB::class.java,
                "articleDB"
            ).build()
    }
}