object Dependency {
    object Spring {
        private val VERSION = Plugin.SPRING_BOOT.version
        private const val SPRINGDOC_VERSION = "2.0.2"

        private const val BASE = "org.springframework.boot"
        fun starter(name: String = "") = "$BASE:spring-boot-starter$name:$VERSION"

        val BOOT_STARTER = starter()
        val BOOT_STARTER_TEST = starter("-test")
        val BOOT_STARTER_WEB = starter("-web")
        val BOOT_STARTER_VALIDATION = starter("-validation")
        val BOOT_STARTER_JPA = starter("-data-jpa")
        val SPRINGDOC = "org.springdoc:springdoc-openapi-starter-webmvc-ui:$SPRINGDOC_VERSION"
    }

    object Kotlin {
        private val VERSION = Plugin.KOTLIN_JVM.version

        private const val BASE = "org.jetbrains.kotlin"
        fun module(name: String) = "$BASE:kotlin-$name:$VERSION"

        val REFLECT = module("reflect")
        val TEST_JUNIT5 = module("test-junit5")
    }

    object Test {
        private const val JUNIT_VERSION = "1.10.2"

        private const val BASE = "org.junit.platform"
        fun junit(name: String) = "$BASE:$name:$JUNIT_VERSION"

        val JUNIT_PLATFORM = junit("junit-platform-launcher")
    }

    object Database {
        private const val MYSQL_VERSION = "8.0.33"

        private const val BASE = "com.mysql"
        fun mysql(name: String) = "$BASE:$name:$MYSQL_VERSION"

        val MYSQL_CONNECTOR = mysql("mysql-connector-j")
    }
    object Util {
        private const val UUID_VERSION = "5.3.5"
        val UUID_CREATOR = "com.github.f4b6a3:uuid-creator:$UUID_VERSION"
    }

    object JDSL {
        private const val JDSL_VERSION = "3.5.4"
        private const val JDSL_STARTER_VERSION = "2.2.1.RELEASE"
        private const val BASE = "com.linecorp.kotlin-jdsl"

        val JPQL_DSL = "$BASE:jpql-dsl:$JDSL_VERSION"
        val JPQL_RENDER = "$BASE:jpql-render:$JDSL_VERSION"
        val SPRING_DATA_STARTER = "$BASE:spring-data-kotlin-jdsl-starter-jakarta:$JDSL_STARTER_VERSION"
    }
}
