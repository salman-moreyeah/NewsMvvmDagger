package com.zevo.newsappdemo.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zevo.newsappdemo.databinding.NoteItemBinding
import com.zevo.newsappdemo.models.ArticlesItem

class NewsAdapter(private val onNoteClicked: (ArticlesItem) -> Unit) :
    ListAdapter<ArticlesItem, NewsAdapter.NoteViewHolder>(ComparatorDiffUtil()) {

    lateinit var  context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        context = parent.context
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: ArticlesItem) {
            binding.title.text = note.title
            binding.desc.text = note.description
            Glide
                .with(context)
                .load(note.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .into(binding.image)

            binding.root.setOnClickListener {
                onNoteClicked(note)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem == newItem
        }
    }
}