package com.example.chatmvicoreimpl.data

sealed class News

class CounterNews(val counter: Int): News()