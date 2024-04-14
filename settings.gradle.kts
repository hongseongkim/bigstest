plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "bigsTest"
include("application","domain","internal")

include("application:inquire-application")
findProject(":application:inquire-application")?.name = "inquire-application"
include("application:sync-application")
findProject(":application:sync-application")?.name = "sync-application"
