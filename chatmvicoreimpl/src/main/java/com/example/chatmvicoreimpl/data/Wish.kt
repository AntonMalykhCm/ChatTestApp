package com.example.chatmvicoreimpl.data


sealed class Wish
/**
 * [Wish] for publishing new message with text [message].
 */
internal data class NewMessageWish(val message: String): Wish()

internal object StartFeature: Wish()