package com.example.chatimpl.data.actions

import com.example.chatapi.ChatAction

data class NewMessageAction(
    val message: String
): ChatAction