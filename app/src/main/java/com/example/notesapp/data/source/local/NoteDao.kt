package com.example.notesapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(note: Note)

}