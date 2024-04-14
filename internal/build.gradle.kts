plugins {
    kotlin("plugin.jpa") version "1.9.23"
}

allOpen{
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")

}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly(project(":domain"))
}
