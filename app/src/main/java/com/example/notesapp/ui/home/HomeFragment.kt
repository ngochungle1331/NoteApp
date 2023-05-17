package com.example.notesapp.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.adapters.NoteAdapter
import com.example.notesapp.ui.note.NoteViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NoteViewModel by viewModels()
    private var myNoteList = arrayListOf<Note>()
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvNote.layoutManager = staggeredGridLayoutManager

        // get all notes
        viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
            myNoteList = noteList as ArrayList<Note>
            adapter = NoteAdapter(requireContext(), noteList)
            binding.rvNote.adapter = adapter
        }

        binding.run {
            // get all notes
            ivFilter.setOnClickListener {
                viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
                    adapter = NoteAdapter(requireContext(), noteList)
                    binding.rvNote.adapter = adapter
                }
            }
            // filter high
            tvFilterHigh.setOnClickListener {
                viewModel.getHighNotes().observe(viewLifecycleOwner) { noteList ->
                    adapter = NoteAdapter(requireContext(), noteList)
                    binding.rvNote.adapter = adapter
                }
            }
            // filter medium
            tvFilterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { noteList ->
                    adapter = NoteAdapter(requireContext(), noteList)
                    binding.rvNote.adapter = adapter
                }
            }
            // filter low
            tvFilterLow.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { noteList ->
                    adapter = NoteAdapter(requireContext(), noteList)
                    binding.rvNote.adapter = adapter
                }
            }
        }

        binding.fbAddNote.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment2)
        }
        return binding.root

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val item = menu.findItem(R.id.menuBtnSearch)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Type a note..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                noteFilter(newText)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun noteFilter(noteQuery: String?) {
        val filteredList = arrayListOf<Note>()
        for (note in myNoteList) {
            if (note.title.contains(noteQuery!!) || note.noteSubtitle.contains(noteQuery)) {
                filteredList.add(note)
            }
        }
        adapter.filterNote(filteredList)

    }


}