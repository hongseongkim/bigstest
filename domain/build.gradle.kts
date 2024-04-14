plugins {
    kotlin("plugin.jpa") version "1.9.23"
}

allOpen{
    annotation("javax.persistence.Entity")
}


dependencies {
    compileOnly("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

