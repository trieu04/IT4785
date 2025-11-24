package com.example.a10

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emails: List<EmailModel>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAvatar: TextView = itemView.findViewById(R.id.tvAvatar)
        val tvSender: TextView = itemView.findViewById(R.id.tvSender)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)

        fun bind(email: EmailModel) {
            tvSender.text = email.sender
            tvTime.text = email.time
            tvSubject.text = email.subject
            tvContent.text = email.content
            tvAvatar.text = email.sender.first().toString().uppercase()

            val background: Drawable = tvAvatar.background
            background.colorFilter = PorterDuffColorFilter(email.color, PorterDuff.Mode.SRC_IN)
            tvAvatar.background = background
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    override fun getItemCount(): Int = emails.size
}
