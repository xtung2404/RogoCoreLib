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
                version = "1.0.1.0" // Nhảy hẳn lên 1.0.1.0 cho sạch cache

                pom.withXml {
                    val dependenciesNode = asNode().get("dependencies") as? groovy.util.Node
                        ?: asNode().appendNode("dependencies")

                    // Lọc ra các dependency đang bị thiếu thông tin (do project local)
                    dependenciesNode.children().forEach { dep ->
                        val dependency = dep as groovy.util.Node
                        val artifactId = dependency.get("artifactId") as? String

                        // Nếu artifactId thuộc danh sách module con của bạn, hãy bổ sung Group và Version
                        if (artifactId != null && artifactId.contains("rogobase")) {
                            // Kiểm tra xem đã có groupId chưa, nếu chưa thì thêm vào
                            if (dependency.get("groupId") == null || (dependency.get("groupId") as groovy.util.NodeList).isEmpty()) {
                                dependency.appendNode("groupId", "com.github.xtung2404")
                            }
                            // Tương tự cho version
                            if (dependency.get("version") == null || (dependency.get("version") as groovy.util.NodeList).isEmpty()) {
                                dependency.appendNode("version", "1.0.1.0")
                            }
                        }
                    }
                }
            }
        }
    }
}