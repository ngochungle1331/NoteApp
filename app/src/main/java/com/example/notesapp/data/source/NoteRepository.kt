package com.example.notesapp.data.source

import androidx.lifecycle.LiveData
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.data.source.local.NoteDao


class NoteRepository(private val noteDao: NoteDao) {
    fun getAllNotes(): LiveData<List<Note>> = noteDao.getAllNotes()


    fun getHighNotes(): LiveData<List<Note>> = noteDao.getHighNotes()


    fun getMediumNotes(): LiveData<List<Note>> = noteDao.getMediumNotes()


    fun getLowNotes(): LiveData<List<Note>> = noteDao.getLowNotes()


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