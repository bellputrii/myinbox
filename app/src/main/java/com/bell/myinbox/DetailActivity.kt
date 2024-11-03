package com.bell.myinbox

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bell.myinbox.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sender = intent.getStringExtra("sender") ?: ""
        val subject = intent.getStringExtra("subject") ?: ""
        val date = intent.getStringExtra("date") ?: ""
        val content = intent.getStringExtra("content") ?: ""

        binding.senderTextView.text = sender
        binding.subjectTextView.text = subject
        binding.dateTextView.text = date
        binding.contentTextView.text = content
    }

    fun onBackPressed(view: View) {
        finish() // Or any other action you'd like
    }
}