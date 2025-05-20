package com.example.dagger2_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger2_app.databinding.ItemNotesLayoutBinding
import com.example.dagger2_app.models.NoteDTO

class NotesAdapter:RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var notes:List<NoteDTO> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNotesLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(notes[position])
    }

    inner class ViewHolder(private val binding:ItemNotesLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(noteDTO: NoteDTO){
            binding.tvTitle.text =  noteDTO.title
            binding.tvDescription.text = noteDTO.description
        }
    }

    fun setList(noteList:List<NoteDTO>){
        notes = noteList
    }
}