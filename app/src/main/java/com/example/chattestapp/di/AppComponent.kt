package com.example.chattestapp.di

import com.example.chattestapp.DeepLinkActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ChatModule::class
    ]
)
interface AppComponent {

    fun inject(deepLinkActivity: DeepLinkActivity)

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}