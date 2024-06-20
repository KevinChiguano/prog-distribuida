plugins {
    id("java")
    id("io.quarkus") version "3.11.1"
}

group = "com.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

val quarkusVersion = "3.11.1"

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusVersion}"))

    implementation("io.quarkus:quarkus-arc") // Implementador de cdi en Quarkus | motor de componentes de negocio
    implementation("io.quarkus:quarkus-resteasy-reactive") // REST | motor de jax-rs
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson") // Gestionar JSON

    implementation("io.quarkus:quarkus-hibernate-orm-panache") // JPA + Hibernate | Parache: repositorio de Spring

    implementation("org.postgresql:postgresql:42.7.3")
    // implementation("com.h2database:h2:2.2.224") // Base de datos
}
