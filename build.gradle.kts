import org.asciidoctor.gradle.jvm.AsciidoctorTask

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
    id("org.asciidoctor.jvm.convert") version "3.3.2"
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

configurations {
    getByName("compileOnly") {
        extendsFrom(configurations["annotationProcessor"])
    }

    create("asciidoctorExt")
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
    implementation(Dependency.Spring.BOOT_STARTER_ACTUATOR)

    // Kotlin
    implementation(Dependency.Kotlin.REFLECT)
    testImplementation(Dependency.Kotlin.TEST_JUNIT5)

    // Test
    testRuntimeOnly(Dependency.Test.JUNIT_PLATFORM)
    testImplementation(Dependency.Test.MOCKK)

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.0")

    // Database
    runtimeOnly(Dependency.Database.MYSQL_CONNECTOR)

    // Util
    implementation(Dependency.Util.UUID_CREATOR)

    // Docs
    implementation(Dependency.Spring.SPRINGDOC)
    add("asciidoctorExt", "org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    // JDSL
    implementation(Dependency.JDSL.JPQL_DSL)
    implementation(Dependency.JDSL.JPQL_RENDER)
    implementation(Dependency.JDSL.SPRING_DATA_JPA_SUPPORTER)

    // Discord
    implementation (Dependency.Discord.WEB_HOOK)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask> {
    enabled = false
}

val snippetsDir = file("build/generated-snippets")

tasks.test {
    outputs.dir(snippetsDir)
}

tasks.named<AsciidoctorTask>("asciidoctor") {
    inputs.dir(snippetsDir)
    configurations("asciidoctorExt")
    sources {
        include("**/index.adoc")
    }
    baseDirFollowsSourceFile()
    dependsOn(tasks.test)
}

tasks.bootJar {
    dependsOn(tasks.named<AsciidoctorTask>("asciidoctor"))
    from(tasks.named<AsciidoctorTask>("asciidoctor").get().outputDir) {
        into("static/docs")
    }
}
