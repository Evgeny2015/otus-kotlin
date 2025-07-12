package ru.otus.otuskotlin.etl.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class TaskLock(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = TaskLock("")
    }
}
