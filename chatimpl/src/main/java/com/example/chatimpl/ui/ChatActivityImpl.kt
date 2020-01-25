package com.example.chatimpl.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.chatapi.ChatActionSupplier
import com.example.chatapi.ChatStore
import com.example.chatapi.R
import com.example.chatapi.ui.ChatActivity
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.di.ChatUiDependencies
import com.example.chatimpl.di.DaggerChatUiComponent
import com.example.dependencyholder.DependencyHolder

class ChatActivityImpl : ChatActivity<ChatStateImpl>() {

    companion object {
        private const val DEPENDENCY_KEY = "chat_ui_dependency_key"
        private const val TAG_FRAGMENT_CHAT_INFO = "TAG_FRAGMENT_CHAT_INFO"
        private const val TAG_FRAGMENT_CHAT_FEED = "TAG_FRAGMENT_CHAT_FEED"
        private const val TAG_FRAGMENT_CHAT_MESSAGE = "TAG_FRAGMENT_CHAT_MESSAGE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!DependencyHolder.contains(DEPENDENCY_KEY)) {
            DependencyHolder.put(
                DEPENDENCY_KEY,
                DaggerChatUiComponent
                    .factory()
                    .create(ChatUiDependenciesImpl(chatActionSupplier, chatStore))
            )
        }

        if (supportFragmentManager.fragments.isEmpty()) {
            installFragments()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            DependencyHolder.release(DEPENDENCY_KEY)
        }
    }

    override fun showChatState(chatState: ChatStateImpl) {

    }

    private fun installFragments() {
        installChatInfoFragment()
        installChatFeedFragment()
        installChatMessageFragment()
    }

    private fun installChatMessageFragment() {
        installFragment(
            R.id.chatMessageLayout,
            ChatMessageFragment(),
            TAG_FRAGMENT_CHAT_MESSAGE
        )
    }

    private fun installChatFeedFragment() {
        installFragment(
            R.id.chatFeedLayout,
            ChatFeedFragment(),
            TAG_FRAGMENT_CHAT_FEED
        )
    }

    private fun installChatInfoFragment() {
        installFragment(
            R.id.chatInfoLayout,
            ChatInfoFragment(),
            TAG_FRAGMENT_CHAT_INFO
        )
    }

    private fun installFragment(
        @IdRes layoutId: Int,
        fragment: Fragment,
        tag: String
    ) {
        val arguments = fragment.arguments ?: Bundle()
        arguments.putString(ChatFragmentBase.ARG_KEY_DEPENDENCY_KEY, DEPENDENCY_KEY)
        fragment.arguments = arguments
        supportFragmentManager
            .beginTransaction()
            .add(layoutId, fragment, tag)
            .commit()
    }

    internal class ChatUiDependenciesImpl(
        private val actionSupplier: ChatActionSupplier,
        private val chatStoreImpl: ChatStore<ChatStateImpl>
    ) : ChatUiDependencies{
        override fun getChatActionSupplier(): ChatActionSupplier = actionSupplier
        override fun getChatStore(): ChatStore<ChatStateImpl> = chatStoreImpl
    }
}