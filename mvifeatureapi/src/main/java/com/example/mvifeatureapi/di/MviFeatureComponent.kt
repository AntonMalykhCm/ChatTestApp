package com.example.mvifeatureapi.di

import com.example.mvifeatureapi.api.State
import com.example.mvifeatureapi.di.modules.DependenciesModule
import com.example.mvifeatureapi.di.modules.MviFeatureModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MviFeatureModule::class,
        DependenciesModule::class
    ]
)
internal interface MviFeatureComponent: MviFeatureApi<State> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance dependencies: MviFeatureDependencies<State>): MviFeatureComponent
    }
}