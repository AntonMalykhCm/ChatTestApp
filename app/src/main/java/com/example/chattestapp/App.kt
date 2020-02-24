package com.example.chattestapp

import android.app.Application
import android.content.Context
import com.example.chattestapp.di.AppComponent
import com.example.chattestapp.di.DaggerAppComponent

class App: Application() {

    companion object {
        fun getAppComponent(context: Context) = (context.applicationContext as App).appComponent
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create()
        appComponent.installRouter()
    }
}