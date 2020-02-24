package com.example.mvifeatureapi.api

/**
 * The one who can react to the intents by producing side-effects.
 * When side effects receive the result then [Middleware] posts a new [Intent]
 * to the [IntentDispatcher].
 */
interface Middleware<S : State> {
    /**
     * Processes incoming [intent] using current [state].
     * When side effects are emitted, the results of them should be posted using [dispatcher].
     * @return [Intent] instance. May be [intent] by itself.
     */
    fun apply(state: S?, intent: Intent, dispatcher: IntentDispatcher): Intent
}