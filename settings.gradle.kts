pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com") }

        flatDir {
            dirs("libs", "${rootProject.projectDir}/rogocore-lib/libs")
        }
    }

}

rootProject.name = "RogoCoreLib"
//include(":app")
include(":rogocore-lib")
//include(":rogobase-lib")
//include(":rogobaseandroid-lib")
//include(":rogobaseapp-lib")
