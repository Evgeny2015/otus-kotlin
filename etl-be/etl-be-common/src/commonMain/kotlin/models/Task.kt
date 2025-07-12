package ru.otus.otuskotlin.etl.common.models

data class Task(
    var id: TaskId = TaskId.NONE,
    var title: String = "",
    var description: String = "",
    var rundate: String = "",
    var interval: String = "",
    var task: String = "",
    var lock: TaskLock = TaskLock.NONE,
    val permissionsClient: MutableSet<TaskPermissionClient> = mutableSetOf()
) {
    fun deepCopy(): Task = copy(
        permissionsClient = permissionsClient.toMutableSet(),
    )

    fun isEmpty() = this == NONE

    companion object {
        private val NONE = Task()
    }
}
