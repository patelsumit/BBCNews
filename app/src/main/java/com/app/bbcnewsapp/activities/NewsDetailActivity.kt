package com.app.bbcnewsapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.bbcnewsapp.databinding.ActivityDetailNewsBinding
import com.app.bbcnewsapp.models.Headline
import com.app.bbcnewsapp.utils.epochToIso8601

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val newsList = intent.getSerializableExtra("news_list") as Headline?
        if (newsList != null) {
            binding.txtHeadLine.text = newsList.headline
            binding.txtIntroduction.text = newsList.introduction
            binding.txtTime.text = epochToIso8601(newsList.updated.toLong())
        }
    }
}