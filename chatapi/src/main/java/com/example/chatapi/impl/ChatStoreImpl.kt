package com.example.chatapi.impl

import androidx.annotation.WorkerThread
import com.example.chatapi.ChatAction
import com.example.chatapi.ChatState
import com.example.chatapi.ChatStore
import com.example.chatapi.di.incoming.ChatMiddleware
import com.example.chatapi.di.incoming.ChatReducer
import com.example.chatapi.domain.ChatDispatcher
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

internal class ChatStoreImpl<State : ChatState> @Inject constructor(
    val actionDispatcher: ChatDispatcher,
    val chatMiddleware: ChatMiddleware<State>,
    val chatReducer: ChatReducer<State>
): ChatStore<State> {

    private val statePublisher: Subject<State> = BehaviorSubject.create()
    private var currentState: State? = null

    init {
        val task = actionDispatcher
            .observeChatAction()
            .observeOn(Schedulers.single())
            .subscribe {
                processNewAction(it)
            }
    }

    @WorkerThread
    private fun processNewAction(action: ChatAction) {
        val finalAction = chatMiddleware.apply(currentState, action, actionDispatcher)
        currentState = chatReducer.reduce(currentState, finalAction).also {
            statePublisher.onNext(it)
        }
    }

    override fun observeChatState(): Observable<State> {
        return statePublisher
    }
}