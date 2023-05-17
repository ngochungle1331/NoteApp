package com.example.notesapp.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.data.source.NoteRepository
import com.example.notesapp.data.source.local.NoteDatabase

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabaseInstance(application).noteDao()
        noteRepository = NoteRepository(noteDao)
    }

    fun getAllNotes(): LiveData<List<Note>> = noteRepository.getAllNotes()

    fun addNote(note: Note) {
        noteRepository.insertNote(note)
    }

    fun deleteNote(id: Int) {
        noteRepository.deleteNote(id)
    }

    fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    fun getHighNotes(): LiveData<List<Note>> = noteRepository.getHighNotes()

    fun getMediumNotes(): LiveData<List<Note>> = noteRepository.getMediumNotes()

    fun getLowNotes(): LiveData<List<Note>> = noteRepository.getLowNotes()
}