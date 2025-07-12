package ru.otus.otuskotlin.etl.common.models

enum class TaskCommand {
    NONE,
    CREATE,
    READ,
    UPDATE,
    DELETE,
    SEARCH,
}