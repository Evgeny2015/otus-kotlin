package ru.otus.otuskotlin.etl.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.etl.common.models.Task
import ru.otus.otuskotlin.etl.common.models.TaskFilter
import ru.otus.otuskotlin.etl.common.models.TaskCommand
import ru.otus.otuskotlin.etl.common.models.TaskError
import ru.otus.otuskotlin.etl.common.models.TaskRequestId
import ru.otus.otuskotlin.etl.common.models.TaskState
import ru.otus.otuskotlin.etl.common.models.TaskWorkMode
//import ru.otus.otuskotlin.etl.common.repo.IRepoAd
import ru.otus.otuskotlin.etl.common.stubs.TaskStubs

data class TaskContext(
    var command: TaskCommand = TaskCommand.NONE,
    var state: TaskState = TaskState.NONE,
    val errors: MutableList<TaskError> = kotlin.collections.mutableListOf(),

    var workMode: TaskWorkMode = TaskWorkMode.PROD,
    var stubCase: TaskStubs = TaskStubs.NONE,

    var requestId: TaskRequestId = TaskRequestId.Companion.NONE,
    var timeStart: Instant = Instant.NONE,
    var taskRequest: Task = Task(),
    var taskFilterRequest: TaskFilter = TaskFilter(),

    var taskResponse: Task = Task(),
    var tasksResponse: MutableList<Task> = kotlin.collections.mutableListOf(),
)
