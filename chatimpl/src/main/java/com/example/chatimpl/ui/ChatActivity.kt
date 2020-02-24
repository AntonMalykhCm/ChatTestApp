package com.example.chatimpl.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatimpl.R
import com.example.chatimpl.di.ChatUiComponent
import com.example.chatimpl.di.ChatUiDependencies
import com.example.chatimpl.di.DaggerChatUiComponent
import com.example.dependencyholder.DependencyHolder
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.router.Router
import javax.inject.Inject
typealias StartIntent = com.example.mvifeatureapi.api.Intent.Start
typealias StopIntent = com.example.mvifeatureapi.api.Intent.Stop

class ChatActivity : AppCompatActivity() {

    companion object {
        internal const val CHAT_DI_GRAPH_DEPENDENCY_KEY =
            "com.example.chatimpl.ui.CHAT_DI_DEPENDENCY_KEY"
        private const val CHAT_DI_GRAPH_KEY = "com.example.chatimpl.ui.CHAT_DI_GRAPH_KEY"
        private const val ROUTER_KEY = "com.example.chatimpl.ui.ROUTER_KEY"
        private const val TAG_FRAGMENT_CHAT_INFO = "TAG_FRAGMENT_CHAT_INFO"
        private const val TAG_FRAGMENT_CHAT_FEED = "TAG_FRAGMENT_CHAT_FEED"
        private const val TAG_FRAGMENT_CHAT_MESSAGE = "TAG_FRAGMENT_CHAT_MESSAGE"

        fun launch(from: Context, dependencies: ChatUiDependencies, routerKey: String) {
            DependencyHolder.put(CHAT_DI_GRAPH_DEPENDENCY_KEY, dependencies)
            from.startActivity(
                Intent(from, ChatActivity::class.java)
                    .putExtra(ROUTER_KEY, routerKey)
            )
        }
    }

    @Inject
    lateinit var dispatcher: IntentDispatcher
    private var isCreationFailed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        isCreationFailed = !installDependencyGraph()
        if (isCreationFailed){
            (DependencyHolder.get(intent.getStringExtra(ROUTER_KEY)) as? Router)
                ?.toChat(this)
            finish()
            return
        }

        (DependencyHolder.get(CHAT_DI_GRAPH_KEY) as? ChatUiComponent)
            ?.inject(this)

        if (savedInstanceState == null) {
            dispatcher
                .dispatch(StartIntent)
                .doOnError { }
                .subscribe()
        }

        if (supportFragmentManager.fragments.isEmpty()) {
            installFragments()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            DependencyHolder.release(CHAT_DI_GRAPH_KEY)
            if (!isCreationFailed){
                dispatcher
                    .dispatch(StopIntent)
                    .doOnError { }
                    .subscribe()
            }
        }
    }

    private fun installFragments() {
        installChatInfoFragment()
        installChatFeedFragment()
        installChatMessageFragment()
    }

    private fun installChatMessageFragment() {
        installFragment(
            R.id.chatMessageLayout,
            ChatMessageFragment(),
            TAG_FRAGMENT_CHAT_MESSAGE
        )
    }

    private fun installChatFeedFragment() {
        installFragment(
            R.id.chatFeedLayout,
            ChatFeedFragment(),
            TAG_FRAGMENT_CHAT_FEED
        )
    }

    private fun installChatInfoFragment() {
        installFragment(
            R.id.chatInfoLayout,
            ChatInfoFragment(),
            TAG_FRAGMENT_CHAT_INFO
        )
    }

    private fun installFragment(
        @IdRes layoutId: Int,
        fragment: Fragment,
        tag: String
    ) {
        val arguments = fragment.arguments ?: Bundle()
        arguments.putString(ChatFragmentBase.ARG_KEY_DEPENDENCY_KEY, CHAT_DI_GRAPH_KEY)
        fragment.arguments = arguments
        supportFragmentManager
            .beginTransaction()
            .add(layoutId, fragment, tag)
            .commit()
    }

    private fun installDependencyGraph(): Boolean {
        if (DependencyHolder.contains(CHAT_DI_GRAPH_KEY)) {
            return true
        }
        if (!DependencyHolder.contains(CHAT_DI_GRAPH_DEPENDENCY_KEY)) {
            return false
        }
        val deps = DependencyHolder.get(CHAT_DI_GRAPH_DEPENDENCY_KEY)
        DependencyHolder.release(CHAT_DI_GRAPH_DEPENDENCY_KEY)
        if (deps !is ChatUiDependencies) {
            return false
        }
        DependencyHolder.put(
            CHAT_DI_GRAPH_KEY,
            DaggerChatUiComponent
                .factory()
                .create(deps)
        )
        return true
    }
}