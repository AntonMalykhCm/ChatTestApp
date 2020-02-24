package com.example.mvifeatureapi.api

interface Reducer<S : State> {
    fun reduce(state: S?, intent: Intent): S
}