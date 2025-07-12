package ru.otus.otuskotlin.etl.common

import kotlinx.datetime.Instant

private val INSTANT_NONE = Instant.fromEpochMilliseconds(Long.MIN_VALUE)
val Instant.Companion.NONE
    get() = ru.otus.otuskotlin.etl.common.INSTANT_NONE