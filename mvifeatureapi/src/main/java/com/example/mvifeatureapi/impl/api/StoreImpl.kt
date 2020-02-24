package com.example.mvifeatureapi.impl.api

import com.example.mvifeatureapi.api.*
import com.example.mvifeatureapi.domain.Schedulers
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

/**
 * [Store] implementation that glues the chain to
 * [intentDispatcher] -> [middleware] -> [reducer] -> [statePublisher]
 */
internal class StoreImpl<S : State> @Inject constructor(
    private val intentDispatcher: IntentDispatcher,
    private val middleware: Middleware<S>,
    private val reducer: Reducer<S>,
    schedulers: Schedulers
    ) : Store<S> {

    private val statePublisher: Subject<S> = BehaviorSubject.create()
    private var currentState: S? = null

    init {
        val task = intentDispatcher
            .observeIntent()
            .subscribeOn(schedulers.newSingle())
            .subscribe { processNewAction(it) }
    }

    override fun observeState(): Observable<S> {
        return statePublisher
    }

    private fun processNewAction(intent: Intent) {
        val finalAction = middleware.apply(currentState, intent, intentDispatcher)
        currentState = reducer.reduce(currentState, finalAction).also {
            statePublisher.onNext(it)
        }
    }
}