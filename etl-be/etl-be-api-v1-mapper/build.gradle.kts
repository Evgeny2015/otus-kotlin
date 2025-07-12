plugins {
    id("build-jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(projects.etlBeApiV1Jackson)
    implementation(projects.etlBeCommon)

    testImplementation(kotlin("test-junit"))
    testImplementation(projects.etlBeStubs)
}
