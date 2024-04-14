import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23" apply false
	kotlin("plugin.spring") version "1.9.23" apply false
	id("org.springframework.boot") version "3.2.4" apply false
	id("io.spring.dependency-management") version "1.1.4"
}




configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}


allprojects{
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}




subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	}

	tasks.getByName("bootJar") {
		enabled = false
	}

	tasks.getByName("jar") {
		enabled = true
	}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}


}


