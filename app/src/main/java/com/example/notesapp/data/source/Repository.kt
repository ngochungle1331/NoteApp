package com.example.notesapp.data.source

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.data.source.local.NoteDao
import com.example.notesapp.data.source.local.NoteDatabase


class Repository(private val noteDao: NoteDao) {
    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }

    fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}