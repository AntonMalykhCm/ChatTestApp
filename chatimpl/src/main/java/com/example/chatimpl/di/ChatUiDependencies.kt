package com.example.chatimpl.di

import com.example.chatimpl.data.ChatState
import com.example.mvifeatureapi.di.MviFeatureApi

/**
 * Dependencies that are required for chat feature operating.
 */
interface ChatUiDependencies {

    /**
     * Provides implementation of [MviFeatureApi].
     */
    fun getMviFeatureApi() : MviFeatureApi<ChatState>
}