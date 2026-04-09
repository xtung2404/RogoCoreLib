// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Plugin fat-aar để gộp các file AAR cục bộ
//        classpath("com.github.kezong:fat-aar:1.3.8")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    id("com.android.library") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
}
