package com.example.mvifeatureapi.domain

import io.reactivex.Scheduler

interface Schedulers {
    fun newSingle() : Scheduler
}