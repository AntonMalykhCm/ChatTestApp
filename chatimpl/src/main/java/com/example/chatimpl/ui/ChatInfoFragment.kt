package com.example.chatimpl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.R

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

    override fun showChatState(chatState: ChatStateImpl) {
        title.text = chatState.headerState.chatName
        messagesCount.text = String.format("%s messages", chatState.headerState.messageCount)
    }
}