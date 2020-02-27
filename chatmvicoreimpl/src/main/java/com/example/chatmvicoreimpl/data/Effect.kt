package com.example.chatmvicoreimpl.data

sealed class Effect
/**
 * [Effect] for rendering message with text [message].
 */
internal data class RenderMessageEffect(
    val message: String,
    val isIncoming: Boolean,
    val isLocal: Boolean,
    val id: Int = getNextMessageId()
) : Effect()

internal object Start : Effect()