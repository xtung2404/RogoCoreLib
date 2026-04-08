plugins {
//    alias(libs.plugins.android.application)
    id("com.android.library")
}

android {
    namespace = "com.example.rogobaseapp_lib"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
//        applicationId = "com.example.rogobaseapp_lib"
        minSdk = 24
        targetSdk = 36
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
//    implementation(group = "", name = "rogobase", ext = "jar")
    implementation(group = "", name = "rogobaseapp", ext = "jar")
//    implementation(group = "", name = "rogobaseandroid-release", ext = "aar")
}