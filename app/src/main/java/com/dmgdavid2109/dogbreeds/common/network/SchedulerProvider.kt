package com.dmgdavid2109.dogbreeds.common.network

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}
