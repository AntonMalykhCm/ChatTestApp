package com.example.chatapi.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapi.*
import com.example.chatapi.di.ChatApiComponent
import com.example.dependencyholder.DependencyHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class ChatActivity<State: ChatState> : AppCompatActivity() {

    internal companion object {
        internal const val KEY_DEPENDENCY_KEY = "KEY_DEPENDENCY_KEY"
    }

    private lateinit var dependencyKey: String

    @Inject
    lateinit var chatActionSupplier: ChatActionSupplier
    @Inject
    lateinit var chatStore: ChatStore<State>

    private var observeChatStateTask: Disposable? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        dependencyKey = intent.getStringExtra(KEY_DEPENDENCY_KEY)!!
        (DependencyHolder.get(dependencyKey)
                as? ChatApiComponent)
            ?.inject(this as ChatActivity<ChatState>)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_api_chat)

        if (savedInstanceState == null) {
            chatActionSupplier.supply(ChatStartIntent).subscribe()
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        observeChatStateTask =
            chatStore.observeChatState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ showChatState(it) }

    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        observeChatStateTask?.dispose()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            DependencyHolder.release(dependencyKey)
        }
    }

    abstract fun showChatState(chatState: State)
}