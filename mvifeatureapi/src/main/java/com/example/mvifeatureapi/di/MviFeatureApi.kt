package com.example.mvifeatureapi.di

import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.State
import com.example.mvifeatureapi.api.Store

interface MviFeatureApi<S : State> {
    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <S : State> get(dependencies: MviFeatureDependencies<S>): MviFeatureApi<S> {
            val api = DaggerMviFeatureComponent
                .factory()
                .create(dependencies as MviFeatureDependencies<State>) as MviFeatureApi<S>
            // this is necessary for preparing internal object graph to be properly exposed
            // by the api.
            api.getStore()
            return api
        }
    }

    fun getStore(): Store<S>
    fun getIntentDispatcher(): IntentDispatcher
}