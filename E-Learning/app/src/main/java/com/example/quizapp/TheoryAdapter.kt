package com.example.quizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class TheoryAdapter(private val theoryList: List<TheoryImages>, private val ctx: Context) :
    RecyclerView.Adapter<TheoryAdapter.TheoryViewHolder>(){

    class TheoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var theoryPic : ImageView = itemView.findViewById(R.id.theoryImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheoryAdapter.TheoryViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.theory_card, parent, false)

        return TheoryAdapter.TheoryViewHolder(view)
    }

    override fun getItemCount() = theoryList.size


    override fun onBindViewHolder(holder: TheoryViewHolder, position: Int) {
        val theoryImage: TheoryImages = theoryList[position]

        // Load the image using the resource ID and set it to the ImageView
        holder.theoryPic.setImageResource(theoryImage.image)
    }

}