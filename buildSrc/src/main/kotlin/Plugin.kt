enum class Plugin(
    val id: String,
    val version: String
) {
    KOTLIN_JVM("org.jetbrains.kotlin.jvm", "1.9.25"),
    KOTLIN_SPRING("org.jetbrains.kotlin.plugin.spring", "1.9.25"),
    KOTLIN_JPA("org.jetbrains.kotlin.plugin.jpa", "1.9.25"),
    KOTLIN_ALLOPEN("org.jetbrains.kotlin.plugin.allopen", "1.9.25"),
    KOTLIN_NOARG("org.jetbrains.kotlin.plugin.noarg", "1.9.25"),
    KTLINT("org.jmailen.kotlinter", "3.16.0"),

    SPRING_BOOT("org.springframework.boot", "3.4.1"),
    SPRING_DEPENDENCY_MANAGEMENT("io.spring.dependency-management", "1.1.7"),

    OPENAPI("org.openapi.generator", "6.5.0"),
    ECLIPSE_APT("com.diffplug.eclipse.apt", "3.26.0");
}
