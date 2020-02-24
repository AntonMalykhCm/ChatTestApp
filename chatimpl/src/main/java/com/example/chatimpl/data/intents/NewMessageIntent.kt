package com.example.chatimpl.data.intents

import com.example.mvifeatureapi.api.Intent

/**
 * [Intent] for publishing new message with text [message].
 */
internal data class NewMessageIntent(val message: String): Intent