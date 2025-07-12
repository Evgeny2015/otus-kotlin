package ru.otus.otuskotlin.etl.common.exceptions

import ru.otus.otuskotlin.etl.common.models.TaskCommand

class UnknownTaskCommand(command: TaskCommand) : Throwable("Wrong command $command at mapping toTransport stage")