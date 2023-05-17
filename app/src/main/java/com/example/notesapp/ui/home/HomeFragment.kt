package com.example.notesapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.Adapters.NoteAdapter
import com.example.notesapp.ui.note.NoteViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
            binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvNote.adapter = NoteAdapter(requireContext(), noteList)
        }

        binding.run {
            ivFilter.setOnClickListener {
                viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
                    binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvNote.adapter = NoteAdapter(requireContext(), noteList)
                }
            }
            tvFilterHigh.setOnClickListener {
                viewModel.getHighNotes().observe(viewLifecycleOwner) { noteList ->
                    binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvNote.adapter = NoteAdapter(requireContext(), noteList)
                }
            }
            tvFilterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { noteList ->
                    binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvNote.adapter = NoteAdapter(requireContext(), noteList)
                }
            }
            tvFilterLow.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { noteList ->
                    binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvNote.adapter = NoteAdapter(requireContext(), noteList)
                }
            }
        }

        binding.fbAddNote.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment2)
        }
        return binding.root

    }


}