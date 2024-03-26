package com.virtualassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class NewsAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<NewsDataModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        // Inflate the news_card layout for each item in the RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        val viewHolder = NewsViewHolder(view)

        // Set click listener for the item view
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]

        // Set the title and author of the news article
        holder.textView.text = currentItem.title
        holder.author.text = currentItem.author

        // Load the image using Glide library, apply rounded corners transformation, and set it to the ImageView
        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl)
            .placeholder(R.drawable.news) // Add this line for the placeholder image
            .transform(RoundedCorners(14))
            .into(holder.image)
    }

    // Update the news data in the adapter
    fun updateNews(updateNews: ArrayList<NewsDataModel>) {
        items.clear()
        items.addAll(updateNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.tvItem)
    val image: ImageView = itemView.findViewById(R.id.newsImage)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked {
    fun onItemClicked (item: NewsDataModel){

    }
}
