package ru.otus.otuskotlin.etl.stubs

import ru.otus.otuskotlin.etl.common.models.Task
import ru.otus.otuskotlin.etl.common.models.TaskId
import ru.otus.otuskotlin.etl.stubs.TaskStubFile.ETL_COPY_FILE


object TaskStub {
    fun get(): Task = ETL_COPY_FILE.copy()

    fun prepareResult(block: Task.() -> Unit): Task = get().apply(block)

    fun prepareSearchList(filter: String) = listOf(
        etlTaskCopy("cp", filter),
        etlTaskCopy("rm", filter),
    )

    private fun etlTaskCopy(id: String, filter: String) =
        etlTask(ETL_COPY_FILE, id = id, filter = filter)

    private fun etlTask(base: Task, id: String, filter: String) = base.copy(
        id = TaskId(id),
        title = "$filter $id",
        description = "desc $filter $id",
    )
}