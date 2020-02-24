package com.example.mvifeatureapi.api

interface Middleware<S : State> {
    fun apply(state: S?, intent: Intent, dispatcher: IntentDispatcher): Intent
}