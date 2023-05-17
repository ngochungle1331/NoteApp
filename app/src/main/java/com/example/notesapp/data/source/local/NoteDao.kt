package com.example.notesapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE notePriority=3")
    fun getHighNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE notePriority=2")
    fun getMediumNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE notePriority=1")
    fun getLowNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(note: Note)

}