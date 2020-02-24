package com.example.chatimpl.data.intents

import com.example.chatimpl.data.getNextMessageId
import com.example.mvifeatureapi.api.Intent

internal data class RenderMessageIntent(
    val message: String,
    val isIncoming: Boolean,
    val isLocal: Boolean,
    val id: Int = getNextMessageId()) : Intent
