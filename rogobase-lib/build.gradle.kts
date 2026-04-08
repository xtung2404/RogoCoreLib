plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogobase_lib"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
//        applicationId = "com.example.rogobase_lib"
        minSdk = 24
        targetSdk = 36
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
    implementation(group = "", name = "rogobase", ext = "jar")
//    implementation(group = "", name = "rogobaseapp", ext = "jar")
//    implementation(group = "", name = "rogobaseandroid-release", ext = "aar")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.xtung2404"
                artifactId = "rogobase-lib" // Phải khớp với tên gọi trong file POM lúc nãy
                version = "1.0.1.3"
            }
        }
    }
}