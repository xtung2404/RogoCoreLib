plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogocore_lib"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    // Nhúng các file JAR (Gradle hỗ trợ tốt)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    
    // Dùng compileOnly cho AAR để IDE nhận class và build không bị lỗi
    // Lưu ý: Project khác dùng thư viện này sẽ cần tự thêm file AAR này nếu muốn dùng tài nguyên của nó
    compileOnly(files("libs/rogobaseandroid-release.aar"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                val releaseComponent = components.findByName("release")
                if (releaseComponent != null) {
                    from(releaseComponent)
                }

                groupId = "com.github.xtung2404"
                artifactId = "RogoCoreLib"
                version = "1.0.2.8"
            }
        }
    }
}
