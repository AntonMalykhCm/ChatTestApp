package com.example.chatimpl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.chatimpl.R
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.data.actions.NewMessageAction

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
            chatActionSupplier.supply(
                NewMessageAction(messageInput.text.toString())
            )
                .subscribe()
        }
        messageInput.addTextChangedListener {
            sendButton.isEnabled = it?.isNotBlank() == true
        }
    }

    override fun showChatState(chatState: ChatStateImpl) {
        messageInput.setText(chatState.messageState.message)
    }
}