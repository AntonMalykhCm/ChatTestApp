package com.example.mvifeatureapi.di

import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.State
import com.example.mvifeatureapi.api.Store

/**
 * Api of the [com.example.mvifeatureapi] module.
 */
interface MviFeatureApi<S : State> {
    companion object {
        /**
         * Provides [MviFeatureApi] implementation.
         */
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

    /**
     * Provides [Store] implementation.
     */
    fun getStore(): Store<S>

    /**
     * Provides [IntentDispatcher] implementation.
     */
    fun getIntentDispatcher(): IntentDispatcher
}