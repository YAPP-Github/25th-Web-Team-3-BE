plugins {
    kotlin("jvm") version Plugin.KOTLIN_JVM.version
    kotlin("plugin.spring") version Plugin.KOTLIN_SPRING.version
    kotlin("plugin.jpa") version Plugin.KOTLIN_JPA.version
    id(Plugin.SPRING_BOOT.id) version Plugin.SPRING_BOOT.version
    id(Plugin.SPRING_DEPENDENCY_MANAGEMENT.id) version Plugin.SPRING_DEPENDENCY_MANAGEMENT.version
    id(Plugin.KOTLIN_ALLOPEN.id) version Plugin.KOTLIN_ALLOPEN.version
    id(Plugin.KOTLIN_NOARG.id) version Plugin.KOTLIN_NOARG.version
    id(Plugin.OPENAPI.id) version Plugin.OPENAPI.version
    id(Plugin.ECLIPSE_APT.id) version Plugin.ECLIPSE_APT.version
    id(Plugin.KTLINT.id) version Plugin.KTLINT.version
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

group = "com.coffee"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation(Dependency.Spring.BOOT_STARTER)
    implementation(Dependency.Spring.BOOT_STARTER_VALIDATION)
    implementation(Dependency.Spring.BOOT_STARTER_WEB)
    implementation(Dependency.Spring.BOOT_STARTER_JPA)
    testImplementation(Dependency.Spring.BOOT_STARTER_TEST)

    // Kotlin
    implementation(Dependency.Kotlin.REFLECT)
    testImplementation(Dependency.Kotlin.TEST_JUNIT5)

    // Test
    testRuntimeOnly(Dependency.Test.JUNIT_PLATFORM)

    // Database
    runtimeOnly(Dependency.Database.MYSQL_CONNECTOR)

    // Util
    implementation(Dependency.Util.UUID_CREATOR)

    // Docs
    implementation(Dependency.Spring.SPRINGDOC)

    // JDSL
    implementation(Dependency.JDSL.JPQL_DSL)
    implementation(Dependency.JDSL.JPQL_RENDER)
    implementation(Dependency.JDSL.SPRING_DATA_STARTER)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask> {
    enabled = false
}
