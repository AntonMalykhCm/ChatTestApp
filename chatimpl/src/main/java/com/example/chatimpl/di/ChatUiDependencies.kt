package com.example.chatimpl.di

import com.example.chatimpl.data.ChatState
import com.example.mvifeatureapi.di.MviFeatureApi

interface ChatUiDependencies {

    fun getMviFeatureApi() : MviFeatureApi<ChatState>
}