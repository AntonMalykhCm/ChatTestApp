package com.example.chatimpl.data.intents

import com.example.chatapi.ChatIntent

internal data class NewMessageIntent(
    val message: String
): ChatIntent