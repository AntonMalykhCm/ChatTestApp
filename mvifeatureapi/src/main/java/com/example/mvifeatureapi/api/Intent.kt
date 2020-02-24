package com.example.mvifeatureapi.api

/**
 * Represents intent for changing current state.
 */
interface Intent {
    /**
     * Intent for starting a feature.
     */
    object Start: Intent

    /**
     * Intent for stopping a feature.
     */
    object Stop: Intent
}