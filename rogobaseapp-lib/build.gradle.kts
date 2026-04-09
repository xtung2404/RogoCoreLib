plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogobaseapp_lib"
    // Sửa lại compileSdk cho chuẩn (không dùng hàm release() nếu nó gây lỗi build)
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

    // THAY THẾ CÁCH KHAI BÁO CŨ:
    // Sử dụng fileTree để nhúng file JAR/AAR trực tiếp vào build.
    // Cách này giúp file POM sạch sẽ, không chứa các dependency có groupId rỗng.
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
//    implementation(group = "", name = "rogobaseapp", ext = "jar")
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
//    implementation(files("libs/rogobaseapp.jar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // Sử dụng findByName để an toàn hơn trong quá trình Gradle đánh giá (evaluate)
                val releaseComponent = components.findByName("release")
                if (releaseComponent != null) {
                    from(releaseComponent)
                }

                groupId = "com.github.xtung2404"
                artifactId = "rogobaseapp-lib"
                version = "1.0.1.5" // Nâng lên 1.0.1.4 đồng bộ với các module khác
            }
        }
    }
}