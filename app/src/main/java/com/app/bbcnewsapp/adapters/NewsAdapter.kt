package com.app.bbcnewsapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.bbcnewsapp.R
import com.app.bbcnewsapp.activities.NewsDetailActivity
import com.app.bbcnewsapp.models.Headline
import com.app.bbcnewsapp.utils.epochToIso8601

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var items = ArrayList<Headline>()
    fun setData(items: ArrayList<Headline>) {
        this.items = items
        notifyDataSetChanged()
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtHeadLine = view.findViewById<TextView>(R.id.txtHeadLine)
        val txtTime = view.findViewById<TextView>(R.id.txtTime)
        val txtIntroduction = view.findViewById<TextView>(R.id.txtIntroduction)

        fun bind(headline: Headline) {
            txtHeadLine.text = headline.headline
            txtTime.text = epochToIso8601(headline.updated.toLong())
            txtIntroduction.text = headline.introduction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout_row, parent, false)
        return NewsViewHolder(view)
    }

    // open news detail activity on click of single row item from list
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, NewsDetailActivity::class.java)
            intent.putExtra("news_list", items[position])
            v.context.startActivity(intent)
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}