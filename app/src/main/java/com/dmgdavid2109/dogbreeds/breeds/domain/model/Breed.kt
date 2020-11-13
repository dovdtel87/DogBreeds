package com.dmgdavid2109.dogbreeds.breeds.domain.model

typealias Breed = String

fun Breed.hasSubBread() = this.contains(' ')

fun Breed.getBread() =
    this.substringBefore(" ", this)

fun Breed.getSubbread() =
    this.substringAfter(" ", "")
