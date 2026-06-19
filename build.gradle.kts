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
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("com.codeborne:selenide:7.15.0")
}

tasks.test {
    useJUnitPlatform()
}