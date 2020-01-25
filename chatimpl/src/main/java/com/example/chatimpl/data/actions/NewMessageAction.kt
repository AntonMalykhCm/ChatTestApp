package com.example.chatimpl.data.actions

import com.example.chatapi.ChatAction

internal data class NewMessageAction(
    val message: String
): ChatAction