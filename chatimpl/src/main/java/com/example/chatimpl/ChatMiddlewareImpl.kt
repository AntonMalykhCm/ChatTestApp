package com.example.chatimpl

import android.util.Log
import com.example.chatimpl.data.ChatState
import com.example.mvifeatureapi.api.Intent
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.Middleware

/**
 * [Middleware] that processes incoming intent through [middlewares].
 */
class ChatMiddlewareImpl(
    private val middlewares: List<Middleware<ChatState>>
) : Middleware<ChatState> {

    override fun apply(state: ChatState?, intent: Intent, dispatcher: IntentDispatcher): Intent {
        val intent = middlewares.fold(
            intent,
            { acc, middleware -> middleware.apply(state, acc, dispatcher) }
        )
        Log.d("ChatMiddlewareImpl", "Intent:\n$intent")
        return intent
    }
}