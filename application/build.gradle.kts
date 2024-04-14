
plugins {
    kotlin("plugin.jpa") version "1.9.23" apply false
}


dependencies {
        implementation(project(":application:inquire-application"))
        implementation(project(":application:sync-application"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly(project(":internal"))
}


allprojects{
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation(project(":domain"))
    }

}



tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
