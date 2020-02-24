package com.example.mvifeatureapi.api

import io.reactivex.Observable

interface Store<S: State> {
    fun observeState(): Observable<S>
}