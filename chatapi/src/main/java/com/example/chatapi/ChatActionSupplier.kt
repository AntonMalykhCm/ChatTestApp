package com.example.chatapi

import io.reactivex.Completable

interface ChatActionSupplier {
    fun supply(intent: ChatIntent): Completable
}