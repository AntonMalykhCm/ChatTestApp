package com.example.chatimpl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chatimpl.data.ChatState
import com.example.chatimpl.R

/**
 * Framgment for rendering chat infos.
 */
internal class ChatInfoFragment : ChatFragmentBase() {

    private lateinit var title: TextView
    private lateinit var messagesCount: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.frament_chat_info, container, false)
        title = view.findViewById(R.id.chatNameTextView)
        messagesCount = view.findViewById(R.id.messageCountTextView)
        return view
    }

    override fun showChatState(chatState: ChatState) {
        title.text = chatState.headerState.chatName
        messagesCount.text = String.format("%s messages", chatState.headerState.messageCount)
    }
}