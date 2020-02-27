package com.example.chatmvicoreimpl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.chatmvicoreimpl.R
import com.example.chatmvicoreimpl.data.ChatState
import com.example.chatmvicoreimpl.data.NewMessageWish
import com.example.chatmvicoreimpl.data.Wish

/**
 * Fragment for rendering the view for message composing.
 */
internal class ChatMessageFragment : ChatFragmentBase() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_chat_message, container, false)
        messageInput = view.findViewById(R.id.messageEditText)
        sendButton = view.findViewById(R.id.sendImageView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendButton.setOnClickListener {
            makeWish(NewMessageWish(messageInput.text.toString()))
        }
        messageInput.addTextChangedListener {
            sendButton.isEnabled = it?.isNotBlank() == true
        }
    }

    override fun showChatState(chatState: ChatState) {
        messageInput.setText(chatState.newMessageState.message)
    }
}