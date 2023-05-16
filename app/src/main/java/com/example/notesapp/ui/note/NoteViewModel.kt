package com.example.notesapp.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.data.source.Repository
import com.example.notesapp.data.source.local.NoteDatabase

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    var repository: Repository

    init {
        val noteDao = NoteDatabase.getDatabaseInstance(application).noteDao()
        repository = Repository(noteDao)
    }

    fun addNote(note: Note) {
        repository.insertNote(note)
    }

    fun deleteNote(id: Int) {
        repository.deleteNote(id)
    }

    fun updateNote(note: Note) {
        repository.updateNote(note)
    }
}