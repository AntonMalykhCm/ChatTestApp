package com.example.mvifeatureapi.api

import io.reactivex.Completable
import io.reactivex.Observable

interface IntentDispatcher{
    fun observeIntent(): Observable<Intent>
    fun dispatch(intent: Intent): Completable
}