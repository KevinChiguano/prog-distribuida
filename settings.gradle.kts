plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "prog-distribuida"
include("ejemplo01_cdi")
include("ejemplo02_jpa")
include("ejemplo03_rest")
include("ejemplo03_spring_rest")
include("ejemplo05_quarkus")
include("ejemplo05_quarkus_autor")
