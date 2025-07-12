package ru.otus.otuskotlin.etl.stubs

import ru.otus.otuskotlin.etl.common.models.Task
import ru.otus.otuskotlin.etl.common.models.TaskId
import ru.otus.otuskotlin.etl.common.models.TaskLock
import ru.otus.otuskotlin.etl.common.models.TaskPermissionClient

object TaskStubFile {
    val ETL_COPY_FILE: Task
        get() = Task(
            id = TaskId("1000"),
            title = "Копирование файла",
            description = "Копирование файла",
            lock = TaskLock("123"),
            task = "cp %1 %2",
            permissionsClient = mutableSetOf(
                TaskPermissionClient.READ,
                TaskPermissionClient.UPDATE,
                TaskPermissionClient.DELETE,
                TaskPermissionClient.MAKE_VISIBLE_PUBLIC,
                TaskPermissionClient.MAKE_VISIBLE_GROUP,
                TaskPermissionClient.MAKE_VISIBLE_OWNER,
            )
        )

    val ETL_REMOVE_FILE: Task
        get() = Task(
            id = TaskId("1001"),
            title = "Удаление файла",
            description = "Удаление файла",
            lock = TaskLock("123"),
            task = "rm %1",
            permissionsClient = mutableSetOf(
                TaskPermissionClient.READ,
                TaskPermissionClient.UPDATE,
                TaskPermissionClient.DELETE,
                TaskPermissionClient.MAKE_VISIBLE_PUBLIC,
                TaskPermissionClient.MAKE_VISIBLE_GROUP,
                TaskPermissionClient.MAKE_VISIBLE_OWNER,
            )
        )
}