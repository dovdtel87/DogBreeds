package com.dmgdavid2109.dogbreeds.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}
