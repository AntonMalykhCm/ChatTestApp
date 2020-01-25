package com.example.chatimpl.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.chatapi.ChatActionSupplier
import com.example.chatapi.ChatStore
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.di.ChatUiComponent
import com.example.dependencyholder.DependencyHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal abstract class ChatFragmentBase : Fragment() {

    internal companion object {
        const val ARG_KEY_DEPENDENCY_KEY = "ChatFragmentBase.KEY_DEPENDENCY_KEY"
    }

    @Inject
    protected lateinit var chatActionSupplier: ChatActionSupplier
    @Inject
    lateinit var chatStore: ChatStore<ChatStateImpl>

    private var observeChatStateTask: Disposable? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        (DependencyHolder
            .get(requireArguments().getString(ARG_KEY_DEPENDENCY_KEY)!!)
                as? ChatUiComponent)
            ?.inject(this)

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

    abstract fun showChatState(chatState: ChatStateImpl)

}