import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

 plugins {
	id("org.springframework.boot") version "2.7.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
//Spring base dependency
	implementation("org.springframework.boot:spring-boot-starter")

	//Sprint dependency for API
	implementation("org.springframework.boot:spring-boot-starter-web")


	//Javac inject. to inject scope bean
	implementation("javax.inject:javax.inject:1")

	//Spring data validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//kotlin dependency
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Spring dependency for persistant API
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//Jackson pour gestion des mapping JSON
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")

	//Spring dependency for postgres
	implementation("org.postgresql:postgresql")

	//Spring dependency for H2
	runtimeOnly("com.h2database:h2")


	//Dependances pour le parsage de chaine d'elements en JSON
	implementation("com.google.code.gson:gson")

	//Librairies pour le numero de téléphone
	implementation("com.googlecode.libphonenumber:libphonenumber:8.12.19")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
