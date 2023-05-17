package com.example.notesapp.ui.create

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.ui.note.NoteViewModel
import java.util.Date


class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    var priority = "1"
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.run {
            ivGreenPriority.setOnClickListener {
                priority = "1"
                ivGreenPriority.setImageResource(R.drawable.ic_done)
                ivYellowPriority.setImageResource(0)
                ivRedPriority.setImageResource(0)
            }
            ivYellowPriority.setOnClickListener {
                priority = "2"
                ivYellowPriority.setImageResource(R.drawable.ic_done)
                ivRedPriority.setImageResource(0)
                ivGreenPriority.setImageResource(0)
            }
            ivRedPriority.setOnClickListener {
                priority = "3"
                ivRedPriority.setImageResource(R.drawable.ic_done)
                ivYellowPriority.setImageResource(0)
                ivGreenPriority.setImageResource(0)
            }
        }

        binding.fbCreateNote.setOnClickListener {
            createNote(it)
        }

        return binding.root
    }

    private fun createNote(it: View?) {
        val title = binding.etNoteTitle.text.toString()
        val subTitle = binding.etNoteSubTitle.text.toString()
        val body = binding.etNoteBody.text.toString()
        val date = Date()
        val noteDate: String = DateFormat.format("MMMM d, yyyy", date.time).toString()
        val note = Note(null, title, subTitle, body, noteDate, priority)
        viewModel.addNote(note)

        Toast.makeText(requireContext(), "Created successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment2)
    }



}