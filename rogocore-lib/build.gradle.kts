plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogocore_lib"
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.example.rogocore_lib"
        minSdk = 24
        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
//    api(project(":rogobase-lib"))
//    api(project(":rogobaseapp-lib"))
//    api(project(":rogobaseandroid-lib"))
//        implementation(group = "", name = "rogobase", ext = "jar")
//    implementation(group = "", name = "rogobaseapp", ext = "jar")
//    implementation(group = "", name = "rogobaseandroid-release", ext = "aar")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.xtung2404"
                artifactId = "RogoCoreLib"
                version = "1.0.1.6" // Tăng version
            }
        }
    }
}