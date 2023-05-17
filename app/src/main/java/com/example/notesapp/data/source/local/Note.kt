package com.example.notesapp.data.source.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String,
    var noteSubtitle: String,
    var noteBody: String,
    var noteDate: String,
    var notePriority: String
) : Parcelable