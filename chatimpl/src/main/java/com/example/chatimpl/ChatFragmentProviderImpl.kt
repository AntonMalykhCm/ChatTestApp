package com.example.chatimpl

import com.example.chatapi.di.incoming.ChatFragmentProvider
import com.example.chatapi.ui.ChatFragmentBase
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.ui.ChatFeedFragment
import com.example.chatimpl.ui.ChatInfoFragment
import com.example.chatimpl.ui.ChatMessageFragment

class ChatFragmentProviderImpl : ChatFragmentProvider<ChatStateImpl> {

    override fun provideChatInfoFragment(): ChatFragmentBase<ChatStateImpl> {
        return ChatInfoFragment()
    }

    override fun provideChatFeedFragment(): ChatFragmentBase<ChatStateImpl> {
        return ChatFeedFragment()
    }

    override fun provideChatMessageFragment(): ChatFragmentBase<ChatStateImpl> {
        return ChatMessageFragment()
    }
}