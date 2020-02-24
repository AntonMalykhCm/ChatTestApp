package com.example.chattestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chattestapp.di.DaggerAppComponent
import com.example.router.Router
import javax.inject.Inject

class DeepLinkActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getAppComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        router.toChat(this)
        finish()
    }
}
