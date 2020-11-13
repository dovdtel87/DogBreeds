package com.dmgdavid2109.dogbreeds.common.ui

fun <T : Any> T.toEvent(): Event<T> {
    return Event(this)
}
