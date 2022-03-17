package com.example.newsapp.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import data.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList:MutableList<Article> = mutableListOf()

    fun setData(newData:MutableList<Article>){
        newsList.clear()
        newsList.addAll(newData)
        notifyDataSetChanged()
    }


    class NewsViewHolder(val item: View):RecyclerView.ViewHolder(item) {

        val title = item.findViewById<TextView>(R.id.title)
        val description = item.findViewById<TextView>(R.id.description)
        val image = item.findViewById<ImageView>(R.id.image)

        fun bind(news: Article){

            Glide.with(item).load(news.urlToImage).centerCrop().into(image)
            title.text = news.title
            description.text = news.description

        }

    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList.elementAt(position))
    }


    override fun getItemCount(): Int {
        return newsList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(convertView)
    }


}