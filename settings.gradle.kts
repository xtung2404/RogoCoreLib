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
        maven { url = uri("https://jitpack.io") }
        
        flatDir {
            // Trỏ đúng vào thư mục libs của module hiện tại
            dirs("rogocore-lib/libs")
        }
    }
}

rootProject.name = "RogoCoreLib"
include(":app")
include(":rogocore-lib")
