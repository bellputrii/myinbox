package com.bell.myinbox

// EmailAdapter.kt
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bell.myinbox.databinding.ItemEmailBinding

class EmailAdapter(private var emails: List<DataClass>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    inner class EmailViewHolder(private val binding: ItemEmailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: DataClass) {
            binding.apply {
                senderTextView.text = email.sender
                subjectTextView.text = email.subject
                dateTextView.text = email.date

                root.setOnClickListener {
                    val intent = Intent(root.context, DetailActivity::class.java).apply {
                        putExtra("sender", email.sender)
                        putExtra("subject", email.subject)
                        putExtra("date", email.date)
                        putExtra("content", email.content)
                    }
                    root.context.startActivity(intent)
                }
            }
        }
    }

    fun updateList(newEmails: List<DataClass>) {
        emails = newEmails
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = ItemEmailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    override fun getItemCount() = emails.size
}

