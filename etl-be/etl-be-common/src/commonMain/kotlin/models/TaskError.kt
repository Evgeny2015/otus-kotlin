package ru.otus.otuskotlin.etl.common.models

//import ru.otus.otuskotlin.etl.logging.common.LogLevel

data class TaskError(
    // error code
    val code: String = "",

    // error group
    val group: String = "",

    // form field
    val field: String = "",

    // debug field, onnle for development
    val message: String = "",
//    val level: LogLevel = LogLevel.ERROR,

    // exception
    val exception: Throwable? = null,
)
