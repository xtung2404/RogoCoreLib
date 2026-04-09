plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogobase_lib"
    compileSdk = 35 // Sửa lại thành số nguyên để tránh lỗi hàm release()

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

    // THAY THẾ CÁCH KHAI BÁO CŨ:
    // Cách này sẽ nhúng file .jar/.aar vào build mà không tạo ra dependency lỗi trong POM
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
                // Sử dụng findByName để an toàn, tránh lỗi cấu hình chưa sẵn sàng
                val releaseComponent = components.findByName("release")
                if (releaseComponent != null) {
                    from(releaseComponent)
                }

                groupId = "com.github.xtung2404"
                artifactId = "rogobase-lib"
                version = "1.0.1.5" // TĂNG LÊN 1.0.1.4 ĐỂ LÀM MỚI CACHE JITPACK
            }
        }
    }
}