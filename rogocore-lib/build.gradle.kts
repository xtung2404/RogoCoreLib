plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.rogocore_lib"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
//        applicationId = "com.example.rogocore_lib"
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
//    api(project(":rogobase-lib"))
//    api(project(":rogobaseapp-lib"))
//    api(project(":rogobaseandroid-lib"))
        implementation(group = "", name = "rogobase", ext = "jar")
    implementation(group = "", name = "rogobaseapp", ext = "jar")
    implementation(group = "", name = "rogobaseandroid-release", ext = "aar")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.xtung2404"
                artifactId = "RogoCoreLib"
                version = "1.0.0.8" // Tăng version lên để tránh cache

                // THÊM ĐOẠN NÀY ĐỂ FIX LỖI POM:
                pom.withXml {
                    val dependenciesNode = asNode().appendNode("dependencies")
                    // Duyệt qua các module con và ép nó ghi đúng định danh
                    listOf("rogobase-lib", "rogobaseapp-lib", "rogobaseandroid-lib").forEach { moduleName ->
                        val depNode = dependenciesNode.appendNode("dependency")
                        depNode.appendNode("groupId", "com.github.xtung2404")
                        // Lưu ý: artifactId ở đây phải khớp với tên module bạn đặt trên JitPack
                        depNode.appendNode("artifactId", moduleName)
                        depNode.appendNode("version", "1.0.0.8")
                        depNode.appendNode("scope", "runtime")
                    }
                }
            }
        }
    }
}