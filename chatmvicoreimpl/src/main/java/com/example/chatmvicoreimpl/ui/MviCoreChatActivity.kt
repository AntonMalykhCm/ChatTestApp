package com.example.chatmvicoreimpl.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatmvicoreimpl.R
import com.example.chatmvicoreimpl.data.ChatState
import com.example.chatmvicoreimpl.di.DaggerMviChatComponent
import com.example.dependencyholder.DependencyHolder
import io.reactivex.functions.Consumer

/**
 * Chat feature activity.
 */
class MviCoreChatActivity : AppCompatActivity() {

    companion object {
        private const val CHAT_DI_GRAPH_DEPENDENCY_KEY =
            "com.example.chatimpl.ui.CHAT_DI_DEPENDENCY_KEY"
        private const val CHAT_DI_GRAPH_KEY = "com.example.chatimpl.ui.CHAT_DI_GRAPH_KEY"
        private const val ROUTER_KEY = "com.example.chatimpl.ui.ROUTER_KEY"
        private const val TAG_FRAGMENT_CHAT_INFO = "TAG_FRAGMENT_CHAT_INFO"
        private const val TAG_FRAGMENT_CHAT_FEED = "TAG_FRAGMENT_CHAT_FEED"
        private const val TAG_FRAGMENT_CHAT_MESSAGE = "TAG_FRAGMENT_CHAT_MESSAGE"

        /**
         * Launches chat feature.
         * [routerKey] is the key for obtaining [Router] from [DependencyHolder],
         * should be used when activity is started after being suppressed by the OS.
         */
        fun launch(from: Context) {
            from.startActivity(
                Intent(from, MviCoreChatActivity::class.java)
            )
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        installDependencyGraph()
        if (supportFragmentManager.fragments.isEmpty()) {
            installFragments()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            DependencyHolder.release(CHAT_DI_GRAPH_KEY)
        }
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
        arguments.putString(
            ChatFragmentBase.ARG_KEY_DEPENDENCY_KEY,
            CHAT_DI_GRAPH_KEY
        )
        fragment.arguments = arguments
        supportFragmentManager
            .beginTransaction()
            .add(layoutId, fragment, tag)
            .commit()
    }

    private fun installDependencyGraph() {
        if (DependencyHolder.contains(CHAT_DI_GRAPH_KEY)) {
            return
        }
        DependencyHolder.put(
            CHAT_DI_GRAPH_KEY,
            DaggerMviChatComponent.factory().create(this.application)
        )
    }

}