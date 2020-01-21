package com.example.chatapi.impl

import com.example.chatapi.ChatAction
import com.example.chatapi.domain.ChatDispatcher
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

internal class ChatDispatcherImpl @Inject constructor() :
    ChatDispatcher {

    private val actionPublisher: Subject<ChatAction> = PublishSubject.create()

    override fun supply(action: ChatAction): Completable {
        return Completable.fromAction {
            actionPublisher.onNext(action)
        }
    }

    override fun observeChatAction(): Observable<ChatAction> {
        return actionPublisher
    }
}