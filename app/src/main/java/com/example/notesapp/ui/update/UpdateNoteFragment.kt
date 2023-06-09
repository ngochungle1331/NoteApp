package com.example.notesapp.ui.update

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.databinding.FragmentUpdateNoteBinding
import com.example.notesapp.ui.note.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class UpdateNoteFragment : Fragment() {
    private val notes by navArgs<UpdateNoteFragmentArgs>()
    private lateinit var binding: FragmentUpdateNoteBinding
    private var priority = "1"
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater, container, false)
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
        setHasOptionsMenu(true)

        binding.etNoteTitle.setText(notes.data.title)
        binding.etNoteSubTitle.setText(notes.data.noteSubtitle)
        binding.etNoteBody.setText(notes.data.noteBody)

        when (notes.data.notePriority) {
            "1" -> {
                priority = "1"
                binding.ivGreenPriority.setImageResource(R.drawable.ic_done)
                binding.ivYellowPriority.setImageResource(0)
                binding.ivRedPriority.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.ivYellowPriority.setImageResource(R.drawable.ic_done)
                binding.ivGreenPriority.setImageResource(0)
                binding.ivRedPriority.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.ivRedPriority.setImageResource(R.drawable.ic_done)
                binding.ivYellowPriority.setImageResource(0)
                binding.ivGreenPriority.setImageResource(0)
            }
        }

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

            fbUpdateNote.setOnClickListener {
                updateNote(it)
            }
        }

        return binding.root
    }

    private fun updateNote(it: View?) {
        val title = binding.etNoteTitle.text.toString()
        val subTitle = binding.etNoteSubTitle.text.toString()
        val body = binding.etNoteBody.text.toString()
        val date = Date()
        val noteDate: String = DateFormat.format("MMMM d, yyyy", date.time).toString()
        val note = Note(notes.data.id, title, subTitle, body, noteDate, priority)
        viewModel.updateNote(note)

        Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_SHORT).show()

        Log.e("update", "title: $title")

        Navigation.findNavController(it!!).navigate(R.id.action_updateNoteFragment_to_homeFragment)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btnDelete) {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(R.layout.dialog_delete)

            val tvYes = bottomSheetDialog.findViewById<TextView>(R.id.btnYes)
            val tvNo = bottomSheetDialog.findViewById<TextView>(R.id.btnNo)

            tvYes?.setOnClickListener {
                viewModel.deleteNote(notes.data.id!!)
                bottomSheetDialog.dismiss()
                findNavController().popBackStack()
            }
            tvNo?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        } else if (item.itemId == android.R.id.home) {
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)


    }


}