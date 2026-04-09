plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogobaseandroid_lib"
    compileSdk = 35 // Sửa lại giá trị Int thay vì dùng hàm release(36) lỗi

    defaultConfig {
        minSdk = 24
        // targetSdk = 35 // Có thể thêm nếu cần
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

    // NẾU rogobaseandroid-release là một file AAR nằm trong thư mục libs:
    // Bạn nên dùng cách này để nó được nhúng vào đúng cách
//    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
//    implementation(files("libs/rogobaseandroid-release.aar"))

//    implementation(group = "", name = "rogobaseandroid-release", ext = "aar")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // Sử dụng findByName để tránh lỗi "release not found"
                val releaseComponent = components.findByName("release")
                if (releaseComponent != null) {
                    from(releaseComponent)
                }

                groupId = "com.github.xtung2404"
                artifactId = "rogobaseandroid-lib"
                version = "1.0.1.5" // TĂNG LÊN 1.0.1.4 ĐỂ ĐỒNG BỘ
            }
        }
    }
}