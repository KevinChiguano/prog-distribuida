plugins {
    id("java")
    id("application")
}

group = "com.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework:spring-context:5.3.9")
    implementation("org.springframework:spring-orm:5.3.9")
    implementation("org.springframework:spring-tx:5.3.9")
    implementation("org.hibernate:hibernate-core:5.5.7.Final")


    implementation("com.h2database:h2:2.2.224")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.sparkjava:spark-core:2.9.4")
}


sourceSets{
    main{
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}

tasks.test {
    useJUnitPlatform()
}