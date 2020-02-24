package com.example.mvifeatureapi.api

/**
 * Modifies state [S] by applying incoming [Intent].
 */
interface Reducer<S : State> {
    /**
     * Modifies the state [S] by applying [intent].
     *
     * @return new state [S]
     */
    fun reduce(state: S?, intent: Intent): S
}