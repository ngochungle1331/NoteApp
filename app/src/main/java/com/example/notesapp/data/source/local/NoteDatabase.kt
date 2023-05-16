package com.example.notesapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        var INSTANCE: NoteDatabase? = null
        fun getDatabaseInstance(context: Context): NoteDatabase {
            if (INSTANCE != null) {
                return INSTANCE as NoteDatabase
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(context, NoteDatabase::class.java, "Notes")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as NoteDatabase
        }
    }
}