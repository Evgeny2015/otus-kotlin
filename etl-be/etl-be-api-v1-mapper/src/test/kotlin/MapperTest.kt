import org.junit.Test
import ru.otus.otuskotlin.etl.api.v1.models.*
import ru.otus.otuskotlin.etl.common.TaskContext
import ru.otus.otuskotlin.etl.common.models.*
import ru.otus.otuskotlin.etl.common.stubs.TaskStubs
import ru.otus.otuskotlin.etl.mappers.v1.*

import ru.otus.otuskotlin.etl.stubs.TaskStub

import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val req = TaskCreateRequest(
            debug = TaskDebug(
                mode = TaskRequestDebugMode.STUB,
                stub = TaskRequestDebugStubs.SUCCESS,
            ),
            task = TaskStub.get().toTransportCreateTask()
        )
        val expected = TaskStub.prepareResult {
            id = TaskId.NONE
            lock =TaskLock.NONE
            permissionsClient.clear()
        }

        val context = TaskContext()
        context.fromTransport(req)

        assertEquals(TaskStubs.SUCCESS, context.stubCase)
        assertEquals(TaskWorkMode.STUB, context.workMode)
        assertEquals(expected, context.taskRequest)
    }

    @Test
    fun toTransport() {
        val context = TaskContext(
            requestId = TaskRequestId("1234"),
            command = TaskCommand.CREATE,
            taskResponse = TaskStub.get(),
            errors = mutableListOf(
                TaskError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = TaskState.RUNNING,
        )

        val req = context.toTransportTask() as TaskCreateResponse

        assertEquals(req.task, TaskStub.get().toTransportTask())
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}