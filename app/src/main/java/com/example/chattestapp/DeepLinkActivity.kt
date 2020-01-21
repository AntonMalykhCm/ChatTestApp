package com.example.chattestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chattestapp.di.DaggerAppComponent
import javax.inject.Inject

class DeepLinkActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {

        val appComponent = DaggerAppComponent.factory().create()
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        router.openChat(this)
        finish()
    }
}
