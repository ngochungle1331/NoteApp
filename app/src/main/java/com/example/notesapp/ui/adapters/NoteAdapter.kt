package com.example.notesapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.source.local.Note
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.ui.home.HomeFragmentDirections

class NoteAdapter(val context: Context, var noteList: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    fun filterNote(filteredList: ArrayList<Note>) {
        noteList = filteredList
        notifyDataSetChanged()
    }
    class NoteViewHolder(var binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val data = noteList[position]
        holder.binding.tvNoteTitle.text = data.title
        holder.binding.tvNoteBody.text = data.noteBody
        holder.binding.tvNoteDate.text = data.noteDate

        when (noteList[position].notePriority) {
            "1" -> {
                holder.binding.ivPriority.setImageResource(R.drawable.custom_green_shape)
            }
            "2" -> {
                holder.binding.ivPriority.setImageResource(R.drawable.custom_yellow_shape)
            }
            "3" -> {
                holder.binding.ivPriority.setImageResource(R.drawable.custom_red_shape)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int = noteList.size
}