package ru.otus.otuskotlin.etl.mappers.v1

import ru.otus.otuskotlin.etl.api.v1.models.*
import ru.otus.otuskotlin.etl.common.TaskContext
import ru.otus.otuskotlin.etl.common.exceptions.UnknownTaskCommand
import ru.otus.otuskotlin.etl.common.models.*

fun TaskContext.toTransportTask(): IResponse = when (val cmd = command) {
    TaskCommand.CREATE -> toTransportCreate()
    TaskCommand.READ -> toTransportRead()
    TaskCommand.UPDATE -> toTransportUpdate()
    TaskCommand.DELETE -> toTransportDelete()
    TaskCommand.SEARCH -> toTransportSearch()
    TaskCommand.NONE -> throw UnknownTaskCommand(cmd)
}

fun TaskContext.toTransportCreate() = TaskCreateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun TaskContext.toTransportRead() = TaskReadResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun TaskContext.toTransportUpdate() = TaskUpdateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun TaskContext.toTransportDelete() = TaskDeleteResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun TaskContext.toTransportSearch() = TaskSearchResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    tasks = tasksResponse.toTransportTask()
)

fun List<Task>.toTransportTask(): List<TaskResponseObject>? = this
    .map { it.toTransportTask() }
    .toList()
    .takeIf { it.isNotEmpty() }

fun Task.toTransportTask(): TaskResponseObject = TaskResponseObject(
    id = id.toTransportTask(),
    title = title.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
)

internal fun TaskId.toTransportTask() = takeIf { it != TaskId.NONE }?.asString()

private fun List<TaskError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportAd() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun TaskError.toTransportAd() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun TaskState.toResult(): ResponseResult? = when (this) {
    TaskState.RUNNING -> ResponseResult.SUCCESS
    TaskState.FAILING -> ResponseResult.ERROR
    TaskState.FINISHING -> ResponseResult.SUCCESS
    TaskState.NONE -> null
}
