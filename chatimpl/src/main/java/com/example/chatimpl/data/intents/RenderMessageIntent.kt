package com.example.chatimpl.data.intents

import com.example.chatimpl.data.getNextMessageId
import com.example.mvifeatureapi.api.Intent

/**
 * [Intent] for rendering message with text [message].
 */
internal data class RenderMessageIntent(
    val message: String,
    val isIncoming: Boolean,
    val isLocal: Boolean,
    val id: Int = getNextMessageId()
) : Intent
