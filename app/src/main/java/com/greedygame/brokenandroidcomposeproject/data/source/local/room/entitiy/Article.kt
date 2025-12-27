package com.greedygame.brokenandroidcomposeproject.data.source.local.room.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)
