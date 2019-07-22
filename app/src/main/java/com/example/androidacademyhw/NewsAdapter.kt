package com.example.androidacademyhw

import android.content.Context
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.androidacademyhw.data.NewsItem

class NewsAdapter(val context: Context): ListAdapter<NewsItem, androidx.recyclerview.widget.RecyclerView.ViewHolder>(NewsItemCallback()) {
    private val imageLoader: RequestManager

    init {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .centerCrop()

        imageLoader = Glide.with(context).applyDefaultRequestOptions(requestOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return VH(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false), imageLoader)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as VH).bind(getItem(position), context as OnClickListener)
    }

    class VH (view: View, private val imageLoader: RequestManager): androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        private val tvCat     = view.findViewById<TextView>(R.id.tv_item_category)
        private val tvHeader  = view.findViewById<TextView>(R.id.tv_item_header)
        private val tvPreview = view.findViewById<TextView>(R.id.tv_item_preview)
        private val tvDate    = view.findViewById<TextView>(R.id.tv_item_time)
        private val ivPic     = view.findViewById<ImageView>(R.id.iv_pic)

        fun bind(item: NewsItem, listener: OnClickListener) {
            tvCat.text = item.category.name
            tvHeader.text = item.title
            tvPreview.text = item.previewText
            tvDate.text = item.publishDate.toString()
            imageLoader.load(item.imageUrl).into(ivPic)

            itemView.setOnClickListener {
                listener.onClick(item)
                Log.d("CLICK", "CLICK")
            }
        }
    }

    class NewsItemCallback: DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(p0: NewsItem, p1: NewsItem): Boolean
                = p0.title == p1.title

        override fun areContentsTheSame(p0: NewsItem, p1: NewsItem): Boolean
                = p0 == p1
    }

    interface OnClickListener {
        fun onClick(item: NewsItem)
    }
}