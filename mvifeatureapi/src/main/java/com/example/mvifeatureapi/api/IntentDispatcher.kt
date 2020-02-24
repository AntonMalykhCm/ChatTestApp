package com.example.mvifeatureapi.api

import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Dispatches incoming intents to the observers.
 */
interface IntentDispatcher{
    /**
     * Provides observable source of the incoming events.
     */
    fun observeIntent(): Observable<Intent>

    /**
     * Notifies observers of [intent]
     */
    fun dispatch(intent: Intent): Completable
}