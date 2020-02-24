package com.example.chatimpl.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatimpl.R
import com.example.chatimpl.data.ChatState

internal class FeedAdapter
    : ListAdapter<ChatState.Message, FeedAdapter.MessageViewHolder>(ItemCallback()) {

    private companion object {
        const val TYPE_INCOMING = 0
        const val TYPE_OUTCOMING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == TYPE_INCOMING) IncomingMessage(parent) else OutcomingMessage(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isIncoming) TYPE_INCOMING else TYPE_OUTCOMING
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.showMessage(getItem(position))
    }

    fun showMessages(messages: List<ChatState.Message>) {
        submitList(messages)
    }

    abstract class MessageViewHolder(parent: ViewGroup, @LayoutRes layoutRes: Int) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        ) {
        abstract fun showMessage(message: ChatState.Message)
    }

    private class IncomingMessage(parent: ViewGroup) :
        MessageViewHolder(parent, R.layout.item_incoming_message) {
        override fun showMessage(message: ChatState.Message) {
            itemView.findViewById<TextView>(R.id.messageTextView).text = message.text
        }
    }

    private class OutcomingMessage(parent: ViewGroup) :
        MessageViewHolder(parent, R.layout.item_outcoming_message) {
        override fun showMessage(message: ChatState.Message) {
            itemView.findViewById<TextView>(R.id.messageTextView).text = message.text
            itemView.findViewById<View>(R.id.clock).isVisible = message.isLocal
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<ChatState.Message>() {
        override fun areItemsTheSame(
            oldItem: ChatState.Message,
            newItem: ChatState.Message
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ChatState.Message,
            newItem: ChatState.Message
        ): Boolean {
            return oldItem == newItem
        }

    }
}