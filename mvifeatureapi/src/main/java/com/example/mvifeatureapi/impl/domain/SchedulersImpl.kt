package com.example.mvifeatureapi.impl.domain

import com.example.mvifeatureapi.domain.Schedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers.*
import java.util.concurrent.Executors
import javax.inject.Inject

class SchedulersImpl @Inject constructor() : Schedulers {
    override fun newSingle(): Scheduler {
        return from(Executors.newSingleThreadExecutor())
    }
}