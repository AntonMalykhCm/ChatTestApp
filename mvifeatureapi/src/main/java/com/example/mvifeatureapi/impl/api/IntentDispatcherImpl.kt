package com.example.mvifeatureapi.impl.api

import com.example.mvifeatureapi.api.Intent
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.domain.Schedulers
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

/**
 * [IntentDispatcher] that uses single thread for processing the queue of intents [Intent].
 * @param schedulers [Schedulers] implementation.
 */
internal class IntentDispatcherImpl @Inject constructor(
    schedulers: Schedulers
) : IntentDispatcher {

    private val intentPublisher: Subject<Intent> = PublishSubject.create()
    private val scheduler = schedulers.newSingle()

    override fun observeIntent(): Observable<Intent> = intentPublisher
    override fun dispatch(intent: Intent): Completable {
        return Completable.fromAction {
            intentPublisher.onNext(intent)
        }
            .subscribeOn(scheduler)
    }
}