package com.example.chatapi.impl

import android.content.Context
import android.content.Intent
import com.example.chatapi.ChatState
import com.example.chatapi.di.outcoming.ChatStarter
import com.example.chatapi.ui.ChatActivity
import javax.inject.Inject

internal class ChatStarterImpl @Inject constructor() : ChatStarter {

    override fun <State : ChatState> startChat(
        context: Context,
        chatClass: Class<out ChatActivity<State>>,
        dependencyKey: String
    ) {
        context.startActivity(
            Intent(context, chatClass)
                .putExtra(ChatActivity.KEY_DEPENDENCY_KEY, dependencyKey)
        )
    }
}