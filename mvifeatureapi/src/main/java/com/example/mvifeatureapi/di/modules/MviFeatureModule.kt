package com.example.mvifeatureapi.di.modules

import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.State
import com.example.mvifeatureapi.api.Store
import com.example.mvifeatureapi.domain.Schedulers
import com.example.mvifeatureapi.impl.api.IntentDispatcherImpl
import com.example.mvifeatureapi.impl.api.StoreImpl
import com.example.mvifeatureapi.impl.domain.SchedulersImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface MviFeatureModule {

    @Binds
    @Singleton
    fun bindSchedulers(impl: SchedulersImpl): Schedulers

    @Binds
    @Singleton
    fun bindDispatcher(impl: IntentDispatcherImpl): IntentDispatcher

    @Binds
    @Singleton
    fun bindStore(impl: StoreImpl<State>): Store<State>
}
