package ru.otus.otuskotlin.etl.mappers.v1.exceptions

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to EtlContext")
