package com.example.chatmvicoreimpl.feature

import android.content.Context
import android.widget.Toast
import com.example.chatmvicoreimpl.data.CounterNews
import com.example.chatmvicoreimpl.data.News
import io.reactivex.functions.Consumer
import javax.inject.Inject

class NewsListener @Inject constructor(
     private val appContext: Context
) : Consumer<News> {

    override fun accept(news: News?) {
        when (news) {
            is CounterNews -> Toast.makeText(appContext, "value ${news.counter}", Toast.LENGTH_SHORT).show()
        }
    }
}