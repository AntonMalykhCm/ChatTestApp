package com.example.chatimpl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatimpl.R
import com.example.chatimpl.data.ChatState
import com.example.chatimpl.ui.view.FeedAdapter

internal class ChatFeedFragment : ChatFragmentBase() {

    private lateinit var feedRecyclerView: RecyclerView
    private val feedAdapter: FeedAdapter by lazy { FeedAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_chat_feed, container, false)
        feedRecyclerView = view.findViewById(R.id.feedRecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedRecyclerView.adapter = feedAdapter
    }

    override fun showChatState(chatState: ChatState) {
        feedAdapter.showMessages(chatState.feedState.messages)
    }
}