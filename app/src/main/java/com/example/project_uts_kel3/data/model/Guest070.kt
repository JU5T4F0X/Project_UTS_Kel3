package com.example.aplikasiuts.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "table_guest_070",
    foreignKeys = [ForeignKey(
        entity = User203::class,
        parentColumns = ["id203"],
        childColumns = ["user_id070"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["user_id070"])]
)
data class Guest070(
    @PrimaryKey(autoGenerate = true) val id070: Int = 0,
    @ColumnInfo(name = "user_id070") val user_id070: Int,
    @ColumnInfo(name = "nama_070") val nama070: String,
    @ColumnInfo(name = "alamat_070") val alamat070: String,
    @ColumnInfo(name = "keperluan_070") val keperluan070: String,
    @ColumnInfo(name = "timestamp_070") val timestamp070: Long
)
