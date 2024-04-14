

plugins {
    kotlin("plugin.jpa") version "1.9.23"
}



tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}


dependencies {
        implementation(project(":application:inquire-application"))
        implementation(project(":application:sync-application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}


allprojects{
    dependencies {
        implementation(project(":domain"))
        runtimeOnly(project(":internal"))
        implementation("org.springframework.boot:spring-boot-starter-web")

    }

}

