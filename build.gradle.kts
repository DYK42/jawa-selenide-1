plugins {
    id("java")
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

// используем 11 версию языка
java {
    sourceCompatibility = JavaVersion.VERSION_21
}

// кодировка файлов (если используете русский язык в файлах)
tasks {
    withType<JavaCompile>().configureEach { options.encoding = "UTF-8" }
    withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
    withType<Test>().configureEach { defaultCharacterEncoding = "UTF-8" }
}

repositories {
    mavenCentral()
}

dependencies {
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // API для написания тестов
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
    // Среда выполнения (движок)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    // Опционально: мост для запуска тестов JUnit 4 (если есть такие)
    // testRuntimeOnly("org.junit.vintage:junit-vintage-engine:6.0.3")
    testImplementation("com.codeborne:selenide:7.15.0")
}

tasks.test {
    useJUnitPlatform()
}