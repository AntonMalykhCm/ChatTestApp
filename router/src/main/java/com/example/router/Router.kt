package com.example.router

import android.content.Context

/**
 * Navigates to features.
 */
interface Router {
    /**
     * Navigates to chat feature from [from].
     */
    fun toChat(from: Context)
}