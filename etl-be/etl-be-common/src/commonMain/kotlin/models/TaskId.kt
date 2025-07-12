package ru.otus.otuskotlin.etl.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class TaskId(private val id: String) {
    fun asString(): String = id

    companion object {
        val NONE = TaskId("")
    }
}
