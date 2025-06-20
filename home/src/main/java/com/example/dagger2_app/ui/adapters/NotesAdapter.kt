package com.example.dagger2_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger2_app.models.NoteDTO
import com.example.home.R
import com.example.home.databinding.ItemNotesLayoutBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    interface ICallback {
        fun remove(noteDTO: NoteDTO)
        fun click(noteDTO: NoteDTO)
    }


    private var listener: ICallback? = null
    private val colors = listOf(R.color.blue, R.color.yellow, R.color.lightGreen, R.color.orange)


    private val diffCallBack = object : DiffUtil.ItemCallback<NoteDTO>() {
        override fun areItemsTheSame(oldItem: NoteDTO, newItem: NoteDTO): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NoteDTO, newItem: NoteDTO): Boolean {
            return oldItem == newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemNotesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }

    inner class ViewHolder(private val binding: ItemNotesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteDTO: NoteDTO) {
            binding.cardView.setCardBackgroundColor(binding.root.resources.getColor(colors[layoutPosition % colors.size]))

            binding.tvTitle.text = noteDTO.title
            binding.tvDescription.text = noteDTO.description
            binding.btnRemove.setOnClickListener {
                listener?.remove(noteDTO)
            }

            itemView.setOnClickListener {
                listener?.click(noteDTO)
            }
        }
    }

    fun setList(noteList: List<NoteDTO>) {
        diffUtil.submitList(noteList)
    }

    fun setCallBack(callBack: ICallback) {
        listener = callBack
    }
}