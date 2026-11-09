package com.example.aplikasiuts.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "table_user_203",
    indices = [Index(value = ["username_203"], unique = true)]
)
data class User203(
    @PrimaryKey(autoGenerate = true) val id203: Int = 0,
    @ColumnInfo(name = "username_203") val username203: String,
    @ColumnInfo(name = "password_203") val password203: String
)
