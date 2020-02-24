package com.example.chatimpl

import com.example.chatimpl.data.ChatState
import com.example.mvifeatureapi.api.Intent
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.Middleware

class ChatMiddlewareImpl(
    private val middlewares: List<Middleware<ChatState>>
) : Middleware<ChatState> {

    override fun apply(state: ChatState?, intent: Intent, dispatcher: IntentDispatcher): Intent {
        return middlewares.fold(
            intent,
            { acc, middleware -> middleware.apply(state, acc, dispatcher) }
        )
    }
}