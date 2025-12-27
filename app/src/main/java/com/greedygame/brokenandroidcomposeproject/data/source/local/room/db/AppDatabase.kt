package com.greedygame.brokenandroidcomposeproject.data.source.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greedygame.brokenandroidcomposeproject.data.source.local.room.entitiy.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
}