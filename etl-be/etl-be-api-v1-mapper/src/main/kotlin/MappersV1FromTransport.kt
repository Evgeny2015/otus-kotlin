package ru.otus.otuskotlin.etl.mappers.v1

import ru.otus.otuskotlin.etl.api.v1.models.*
import ru.otus.otuskotlin.etl.common.models.*
import ru.otus.otuskotlin.etl.common.TaskContext
import ru.otus.otuskotlin.etl.common.stubs.TaskStubs
import ru.otus.otuskotlin.etl.mappers.v1.exceptions.UnknownRequestClass


fun TaskContext.fromTransport(request: IRequest) = when (request) {
    is TaskCreateRequest -> fromTransport(request)
    is TaskReadRequest -> fromTransport(request)
    is TaskUpdateRequest -> fromTransport(request)
    is TaskDeleteRequest -> fromTransport(request)
    is TaskSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun String?.toTaskId() = this?.let { TaskId(it) } ?: TaskId.NONE
private fun String?.toTaskWithId() = Task(id = this.toTaskId())
private fun String?.toTaskLock() = this?.let { TaskLock(it) } ?: TaskLock.NONE

private fun TaskDebug?.transportToWorkMode(): TaskWorkMode = when (this?.mode) {
    TaskRequestDebugMode.PROD -> TaskWorkMode.PROD
    TaskRequestDebugMode.TEST -> TaskWorkMode.TEST
    TaskRequestDebugMode.STUB -> TaskWorkMode.STUB
    null -> TaskWorkMode.PROD
}

private fun TaskDebug?.transportToStubCase(): TaskStubs = when (this?.stub) {
    TaskRequestDebugStubs.SUCCESS -> TaskStubs.SUCCESS
    TaskRequestDebugStubs.NOT_FOUND -> TaskStubs.NOT_FOUND
    TaskRequestDebugStubs.BAD_ID -> TaskStubs.BAD_ID
    TaskRequestDebugStubs.BAD_TITLE -> TaskStubs.BAD_TITLE
    TaskRequestDebugStubs.BAD_DESCRIPTION -> TaskStubs.BAD_DESCRIPTION
    TaskRequestDebugStubs.BAD_VISIBILITY -> TaskStubs.BAD_VISIBILITY
    TaskRequestDebugStubs.CANNOT_DELETE -> TaskStubs.CANNOT_DELETE
    TaskRequestDebugStubs.BAD_SEARCH_STRING -> TaskStubs.BAD_SEARCH_STRING
    null -> TaskStubs.NONE
}

fun TaskContext.fromTransport(request: TaskCreateRequest) {
    command = TaskCommand.CREATE
    taskRequest = request.task?.toInternal() ?: Task()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun TaskContext.fromTransport(request: TaskReadRequest) {
    command = TaskCommand.READ
    taskRequest = request.task.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun TaskReadObject?.toInternal(): Task = if (this != null) {
    Task(id = id.toTaskId())
} else {
    Task()
}


fun TaskContext.fromTransport(request: TaskUpdateRequest) {
    command = TaskCommand.UPDATE
    taskRequest = request.task?.toInternal() ?: Task()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun TaskContext.fromTransport(request: TaskDeleteRequest) {
    command = TaskCommand.DELETE
    taskRequest = request.task.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun TaskDeleteObject?.toInternal(): Task = if (this != null) {
    Task(
        id = id.toTaskId(),
        lock = lock.toTaskLock(),
    )
} else {
    Task()
}

fun TaskContext.fromTransport(request: TaskSearchRequest) {
    command = TaskCommand.SEARCH
    taskFilterRequest = request.adFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun TaskSearchFilter?.toInternal(): TaskFilter = TaskFilter(
    searchString = this?.searchString ?: "",
)

private fun TaskCreateObject.toInternal(): Task = Task(
    title = this.title ?: "",
    description = this.description ?: "",
    task = this.task ?: "",
)

private fun TaskUpdateObject.toInternal(): Task = Task(
    id = this.id.toTaskId(),
    title = this.title ?: "",
    description = this.description ?: "",
    lock = lock.toTaskLock(),
)

