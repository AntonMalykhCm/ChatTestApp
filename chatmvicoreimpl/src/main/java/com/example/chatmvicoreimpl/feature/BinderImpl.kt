package com.example.chatmvicoreimpl.feature

import com.badoo.mvicore.android.AndroidBindings
import com.example.chatmvicoreimpl.ui.ChatFragmentBase

internal class BinderImpl(
    view: ChatFragmentBase,
    private val chatFeature: ChatFeature,
    private val newsListener: NewsListener
) : AndroidBindings<ChatFragmentBase>(view) {

    override fun setup(view: ChatFragmentBase) {
        binder.bind(chatFeature to view)
        binder.bind(view to chatFeature)
        binder.bind(chatFeature.news to newsListener)
    }

}