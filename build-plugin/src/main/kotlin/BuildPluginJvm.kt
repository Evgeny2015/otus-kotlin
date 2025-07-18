package ru.otus.otuskotlin.etl.plugin

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

@Suppress("unused")
internal class BuildPluginJvm : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")
//        pluginManager.apply(KotlinPlatformJvmPlugin::class.java)
        val libs = project.the<LibrariesForLibs>()
        tasks.withType(KotlinJvmCompile::class.java).configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.valueOf("JVM_" + libs.versions.jvm.compiler.get()))
            }
        }
        group = rootProject.group
        version = rootProject.version
        repositories {
            mavenCentral()
        }
    }
}
