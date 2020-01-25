package com.example.chatapi

import android.content.Context
import com.example.chatapi.ui.ChatActivity

interface ChatStarter {
    fun <State: ChatState> startChat(
        context: Context,
        chatClass: Class<out ChatActivity<State>>,
        dependencyKey: String
    )
}