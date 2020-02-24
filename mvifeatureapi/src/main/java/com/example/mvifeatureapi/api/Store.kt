package com.example.mvifeatureapi.api

import io.reactivex.Observable

/**
 * Reactive state [S] holder.
 */
interface Store<S: State> {
    /**
     * Provides current state [S].
     */
    fun observeState(): Observable<S>
}