package com.example.mvifeatureapi.di

import com.example.mvifeatureapi.api.Middleware
import com.example.mvifeatureapi.api.Reducer
import com.example.mvifeatureapi.api.State

/**
 * Dependencies that are required for making [MviFeatureApi].
 */
interface MviFeatureDependencies<S: State> {
    /**
     * Provides [Middleware] implementation.
     */
    fun getMiddleware(): Middleware<S>

    /**
     * Provides [Reducer] implementation.
     */
    fun getReducer(): Reducer<S>
}