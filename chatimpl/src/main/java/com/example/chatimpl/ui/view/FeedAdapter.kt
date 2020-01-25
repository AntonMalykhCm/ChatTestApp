package com.example.chatimpl.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.chatimpl.R
import com.example.chatimpl.data.ChatStateImpl

internal class FeedAdapter : RecyclerView.Adapter<FeedAdapter.MessageViewHolder>() {

    private companion object{
        const val TYPE_INCOMING = 0
        const val TYPE_OUTCOMING = 1
    }

    private val messages = mutableListOf<ChatStateImpl.Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == TYPE_INCOMING) IncomingMessage(parent) else OutcomingMessage(parent)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isIncoming) TYPE_INCOMING else TYPE_OUTCOMING
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.showMessage(messages[position])
    }

    fun showMessages(messages: List<ChatStateImpl.Message>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }

    abstract class MessageViewHolder(parent: ViewGroup, @LayoutRes layoutRes: Int)
        : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    ) {
        abstract fun showMessage(message: ChatStateImpl.Message)
    }

    private class IncomingMessage(parent: ViewGroup)
        : MessageViewHolder(parent, R.layout.item_incoming_message) {
        override fun showMessage(message: ChatStateImpl.Message) {
            itemView.findViewById<TextView>(R.id.messageTextView).text = message.text
        }
    }

    private class OutcomingMessage(parent: ViewGroup)
        : MessageViewHolder(parent, R.layout.item_outcoming_message) {
        override fun showMessage(message: ChatStateImpl.Message) {
            itemView.findViewById<TextView>(R.id.messageTextView).text = message.text
        }
    }
}