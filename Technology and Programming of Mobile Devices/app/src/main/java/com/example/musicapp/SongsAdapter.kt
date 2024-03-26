package com.example.musicapp

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongsAdapter(private val songs: List<MediaItem>, private val ctx: Context) :
    RecyclerView.Adapter<SongsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.song_thumbnail)
        val songName: TextView = itemView.findViewById(R.id.song_title)
        val artistName: TextView = itemView.findViewById(R.id.song_artist)
        val albumName: TextView = itemView.findViewById(R.id.song_album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_thumbnails, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // This method retrieves data and displays inside the view (i.e. Card) while binding
        val song = songs[position]

        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(null, song.description.mediaUri)

        metadataRetriever.embeddedPicture?.let {
            val bmp = BitmapFactory.decodeByteArray(
                it,
                0,
                it.size
            )

            holder.imageView.setImageBitmap(bmp)
        }

        holder.songName.text = song.description.title
        holder.artistName.text = song.description.subtitle
        holder.albumName.text = song.description.description

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, SongPlayer::class.java)
            intent.putExtra("mediaId", songs[position].mediaId)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount() = songs.size
}
