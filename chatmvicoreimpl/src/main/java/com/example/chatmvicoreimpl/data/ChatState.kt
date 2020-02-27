package com.example.chatmvicoreimpl.data

private var ID_COUNTER = 0

/**
 * Provides id for the message.
 */
internal fun getNextMessageId() = ID_COUNTER++

/**
 * State of the chat feature.
 */
data class ChatState(
    val headerState: HeaderState,
    val feedState: FeedState,
    val newMessageState: NewMessageState
) {

    companion object {
        fun getDefaultChatState(): ChatState {
            return ChatState(
                ChatState.HeaderState(
                    "Чад кутежа",
                    3
                ),
                ChatState.FeedState(
                    listOf(
                        ChatState.Message(
                            true,
                            "Привет!",
                            false,
                            getNextMessageId()
                        ),
                        ChatState.Message(
                            false,
                            "Привет!",
                            false,
                            getNextMessageId()
                        ),
                        ChatState.Message(
                            true,
                            "Как дела?",
                            false,
                            getNextMessageId()
                        )
                    )
                ),
                ChatState.NewMessageState(
                    "Отлич"
                )
            )
        }
    }

    data class HeaderState(
        val chatName: String,
        val messageCount: Int
    )

    data class FeedState(
        val messages: List<Message>
    )

    data class NewMessageState(
        val message: String
    )

    data class Message(
        val isIncoming: Boolean,
        val text: String,
        val isLocal: Boolean,
        val id: Int
    )
}