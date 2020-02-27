package com.example.chatmvicoreimpl.feature

import com.badoo.mvicore.element.Bootstrapper
import com.example.chatmvicoreimpl.data.StartFeature
import com.example.chatmvicoreimpl.data.Wish
import io.reactivex.Observable
import javax.inject.Inject

internal class BootstrapperImpl @Inject constructor()
    : Bootstrapper<Wish> {

    override fun invoke(): Observable<Wish> {
        return Observable.just(StartFeature)
    }


}