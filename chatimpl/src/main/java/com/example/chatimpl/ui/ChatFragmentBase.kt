package com.example.chatimpl.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.chatimpl.data.ChatState
import com.example.chatimpl.di.ChatUiComponent
import com.example.dependencyholder.DependencyHolder
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.Store
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Base chat feed fragment.
 */
internal abstract class ChatFragmentBase : Fragment() {

    internal companion object {
        const val ARG_KEY_DEPENDENCY_KEY = "ChatFragmentBase.KEY_DEPENDENCY_KEY"
    }

    @Inject
    protected lateinit var chatDispatcher: IntentDispatcher
    @Inject
    lateinit var chatStore: Store<ChatState>

    private var observeChatStateTask: Disposable? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (DependencyHolder
            .get(requireArguments().getString(ARG_KEY_DEPENDENCY_KEY)!!)
                as? ChatUiComponent)
            ?.inject(this)
    }

    override fun onStart() {
        super.onStart()
        observeChatStateTask =
            chatStore.observeState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ showChatState(it) }

    }

    override fun onStop() {
        super.onStop()
        observeChatStateTask?.dispose()
    }

    abstract fun showChatState(chatState: ChatState)

}