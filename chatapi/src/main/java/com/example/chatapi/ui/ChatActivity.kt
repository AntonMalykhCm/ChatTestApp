package com.example.chatapi.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatapi.*
import com.example.chatapi.di.ChatApiComponent
import com.example.chatapi.di.incoming.ChatFragmentProvider
import com.example.dependencyholder.DependencyHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class ChatActivity<State: ChatState> : AppCompatActivity() {

    internal companion object {
        internal const val KEY_DEPENDENCY_KEY = "KEY_DEPENDENCY_KEY"
        private const val TAG_FRAGMENT_CHAT_INFO = "TAG_FRAGMENT_CHAT_INFO"
        private const val TAG_FRAGMENT_CHAT_FEED = "TAG_FRAGMENT_CHAT_FEED"
        private const val TAG_FRAGMENT_CHAT_MESSAGE = "TAG_FRAGMENT_CHAT_MESSAGE"
    }

    @Inject
    lateinit var chatFragmentProvider: ChatFragmentProvider<State>
    @Inject
    lateinit var chatActionSupplier: ChatActionSupplier
    @Inject
    lateinit var chatStore: ChatStore<State>

    private var observeChatStateTask: Disposable? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        val dependencyKey = intent.getStringExtra(KEY_DEPENDENCY_KEY)!!
        (DependencyHolder.get(dependencyKey)
                as? ChatApiComponent)
            ?.inject(this as ChatActivity<ChatState>)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_api_chat)

        if (savedInstanceState == null) {
            installFragments(dependencyKey)
            chatActionSupplier.supply(ChatStartAction).subscribe()
        }
    }

    override fun onStart() {
        super.onStart()
        observeChatStateTask =
            chatStore.observeChatState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ showChatState(it) }

    }

    override fun onStop() {
        super.onStop()
        observeChatStateTask?.dispose()
    }

    abstract fun showChatState(chatState: State)

    private fun installFragments(dependencyKey: String) {
        installChatInfoFragment(dependencyKey)
        installChatFeedFragment(dependencyKey)
        installChatMessageFragment(dependencyKey)
    }

    private fun installChatMessageFragment(dependencyKey: String) {
        installFragment(
            R.id.chatMessageLayout,
            chatFragmentProvider.provideChatMessageFragment(),
            TAG_FRAGMENT_CHAT_MESSAGE,
            dependencyKey
        )
    }

    private fun installChatFeedFragment(dependencyKey: String) {
        installFragment(
            R.id.chatFeedLayout,
            chatFragmentProvider.provideChatFeedFragment(),
            TAG_FRAGMENT_CHAT_FEED,
            dependencyKey
        )
    }

    private fun installChatInfoFragment(dependencyKey: String) {
        installFragment(
            R.id.chatInfoLayout,
            chatFragmentProvider.provideChatInfoFragment(),
            TAG_FRAGMENT_CHAT_INFO,
            dependencyKey
        )
    }

    private fun installFragment(
        @IdRes layoutId: Int,
        fragment: Fragment,
        tag: String,
        dependencyKey: String
    ) {
        val arguments = fragment.arguments ?: Bundle()
        arguments.putString(KEY_DEPENDENCY_KEY, dependencyKey)
        fragment.arguments = arguments
        supportFragmentManager
            .beginTransaction()
            .add(layoutId, fragment, tag)
            .commit()
    }
}