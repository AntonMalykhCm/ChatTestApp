package com.example.mvifeatureapi.di.modules

import com.example.mvifeatureapi.api.Middleware
import com.example.mvifeatureapi.api.Reducer
import com.example.mvifeatureapi.api.State
import com.example.mvifeatureapi.di.MviFeatureDependencies
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DependenciesModule {

    @Provides
    @Singleton
    fun provideMiddleware(deps: MviFeatureDependencies<State>): Middleware<State> {
        return deps.getMiddleware()
    }

    @Provides
    @Singleton
    fun provideReducer(deps: MviFeatureDependencies<State>): Reducer<State> {
        return deps.getReducer()
    }
}