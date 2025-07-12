package ru.otus.otuskotlin.etl.common.models

data class TaskFilter(
    var searchString: String = "",
) {
    fun deepCopy(): TaskFilter = copy()

    fun isEmpty() = this == NONE

    companion object {
        private val NONE = TaskFilter()
    }
}
