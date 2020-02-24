package com.example.mvifeatureapi.domain

import io.reactivex.Scheduler

internal interface Schedulers {
    fun newSingle() : Scheduler
}