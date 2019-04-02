package com.infisoln.siddhant.firebase_demo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

class RecyclerViewAdapter internal constructor(private val notesArrayList: ArrayList<Notes>) : RecyclerView.Adapter<RecyclerViewAdapter.NotesHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NotesHolder {
        val inflatedView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row, viewGroup, false)
        return NotesHolder(inflatedView)
    }

    override fun onBindViewHolder(notesHolder: NotesHolder, i: Int) {

        val currentNote = notesArrayList[i]
        notesHolder.tvTitle.text = currentNote.title
        notesHolder.tvSubtitle.text = currentNote.subtitle

    }

    override fun getItemCount(): Int {
        return notesArrayList.size
    }

    inner class NotesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)

    }

}
