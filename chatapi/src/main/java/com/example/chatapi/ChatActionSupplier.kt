package com.example.chatapi

import io.reactivex.Completable

interface ChatActionSupplier {
    fun supply(action: ChatAction): Completable
}