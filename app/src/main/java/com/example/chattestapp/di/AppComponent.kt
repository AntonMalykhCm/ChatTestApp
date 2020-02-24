package com.example.chattestapp.di

import com.example.chattestapp.DeepLinkActivity
import com.example.router.Router
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
    fun installRouter(): Router

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}