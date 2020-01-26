package com.example.chatapi.impl

import com.example.chatapi.ChatIntent
import com.example.chatapi.ChatDispatcher
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

internal class ChatDispatcherImpl @Inject constructor() :
    ChatDispatcher {

    private val intentPublisher: Subject<ChatIntent> = PublishSubject.create()

    override fun supply(intent: ChatIntent): Completable {
        return Completable.fromAction {
            intentPublisher.onNext(intent)
        }
    }

    override fun observeChatAction(): Observable<ChatIntent> {
        return intentPublisher
    }
}