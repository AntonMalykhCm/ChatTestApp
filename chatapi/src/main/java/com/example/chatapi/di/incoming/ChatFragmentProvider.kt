package com.example.chatapi.di.incoming

import com.example.chatapi.ChatState
import com.example.chatapi.ui.ChatFragmentBase

interface ChatFragmentProvider<State : ChatState> {

    fun provideChatInfoFragment(): ChatFragmentBase<State>
    fun provideChatFeedFragment(): ChatFragmentBase<State>
    fun provideChatMessageFragment(): ChatFragmentBase<State>
}