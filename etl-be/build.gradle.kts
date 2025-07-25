plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "ru.otus.otuskotlin.etl"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}

ext {
    val specDir = layout.projectDirectory.dir("../specs")
    set("spec-v1", specDir.file("specs-etl-v1.yaml").toString())
}

tasks {
    arrayOf("build", "clean", "check").forEach { tsk ->
        register(tsk) {
            group = "build"
            dependsOn(subprojects.map { it.getTasksByName(tsk, false) })
        }
    }
//    register("buildImages") {
//        dependsOn(project("ok-marketplace-app-spring").tasks.getByName("bootBuildImage"))
//        dependsOn(project("ok-marketplace-app-ktor").tasks.getByName("publishImageToLocalRegistry"))
//        dependsOn(project("ok-marketplace-app-ktor").tasks.getByName("dockerBuildX64Image"))
//    }
}

//kotlin {
//    jvmToolchain(21)
//}