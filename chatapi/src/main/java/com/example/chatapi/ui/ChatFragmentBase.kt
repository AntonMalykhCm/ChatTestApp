package com.example.chatapi.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.chatapi.ChatActionSupplier
import com.example.chatapi.ChatState
import com.example.chatapi.ChatStore
import com.example.chatapi.di.ChatApiComponent
import com.example.dependencyholder.DependencyHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class ChatFragmentBase<State : ChatState> : Fragment() {

    @Inject
    protected lateinit var chatActionSupplier: ChatActionSupplier
    @Inject
    lateinit var chatStore: ChatStore<State>

    private var observeChatStateTask: Disposable? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        (DependencyHolder
            .get(requireArguments().getString(ChatActivity.KEY_DEPENDENCY_KEY)!!)
                as? ChatApiComponent)
            ?.inject(this as ChatFragmentBase<ChatState>)

        super.onCreate(savedInstanceState)
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

}