package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context : Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =  NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notes,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.OnItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNode = allNotes[position]
        holder.textView.text = currentNode.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }


}

interface INotesRVAdapter{
    fun OnItemClick( note : Note)
}