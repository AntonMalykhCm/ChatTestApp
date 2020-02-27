package com.example.chatmvicoreimpl.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.badoo.mvicore.binder.Binder
import com.example.chatmvicoreimpl.data.ChatState
import com.example.chatmvicoreimpl.data.Wish
import com.example.chatmvicoreimpl.di.MviChatComponent
import com.example.chatmvicoreimpl.feature.BinderImpl
import com.example.chatmvicoreimpl.feature.ChatFeature
import com.example.chatmvicoreimpl.feature.NewsListener
import com.example.dependencyholder.DependencyHolder
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Base chat feed fragment.
 */
internal abstract class ChatFragmentBase
    : Fragment(),
    Consumer<ChatState>,
    ObservableSource<Wish> {

    internal companion object {
        const val ARG_KEY_DEPENDENCY_KEY = "ChatFragmentBase.KEY_DEPENDENCY_KEY"
    }

    @Inject
    lateinit var chatFeature: ChatFeature
    @Inject
    lateinit var newsListener: NewsListener
    private val source = PublishSubject.create<Wish>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (DependencyHolder
            .get(requireArguments().getString(ARG_KEY_DEPENDENCY_KEY)!!)
                as? MviChatComponent)
            ?.inject(this)
        BinderImpl(this, chatFeature, newsListener).setup(this)
    }

    final override fun accept(state: ChatState?) {
        state?.let {
            showChatState(it)
        }
    }

    fun makeWish(wish: Wish) {
        onNext(wish)
    }

    private fun onNext(wish: Wish) {
        source.onNext(wish)
    }

    override fun subscribe(observer: Observer<in Wish>) {
        source.subscribe(observer)
    }

    abstract fun showChatState(chatState: ChatState)

}