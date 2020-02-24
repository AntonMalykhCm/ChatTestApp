package com.example.mvifeatureapi.di

import com.example.mvifeatureapi.api.Middleware
import com.example.mvifeatureapi.api.Reducer
import com.example.mvifeatureapi.api.State

interface MviFeatureDependencies<S: State> {
    fun getMiddleware(): Middleware<S>
    fun getReducer(): Reducer<S>
}