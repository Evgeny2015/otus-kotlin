package ru.otus.otuskotlin.etl.mappers.v1

import ru.otus.otuskotlin.etl.api.v1.models.TaskCreateObject
import ru.otus.otuskotlin.etl.api.v1.models.TaskDeleteObject
import ru.otus.otuskotlin.etl.api.v1.models.TaskReadObject
import ru.otus.otuskotlin.etl.api.v1.models.TaskUpdateObject
import ru.otus.otuskotlin.etl.common.models.Task
import ru.otus.otuskotlin.etl.common.models.TaskId
import ru.otus.otuskotlin.etl.common.models.TaskLock

fun Task.toTransportCreateTask() = TaskCreateObject(
    title = title,
    description = description,
    task = task
)

fun Task.toTransportReadTask() = TaskReadObject(
    id = id.toTransportTask()
)

fun Task.toTransportUpdateTask() = TaskUpdateObject(
    id = id.toTransportTask(),
    title = title,
    description = description,
    lock = lock.toTransportTask(),
)

internal fun TaskLock.toTransportTask() = takeIf { it != TaskLock.NONE }?.asString()

fun Task.toTransportDeleteTask() = TaskDeleteObject(
    id = id.toTransportTask(),
    lock = lock.toTransportTask(),
)