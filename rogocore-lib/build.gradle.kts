plugins {
    id("com.android.library")
    id("maven-publish")}

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

// --- PHẦN XỬ LÝ ĐẶC BIỆT ĐỂ NHÚNG AAR GIỐNG NHƯ JAR ---
val extractedAarDir = layout.buildDirectory.dir("intermediates/extracted_aar_jar")
val extractAarJarTask = tasks.register<Copy>("extractAarJar") {
    val aarFile = file("libs/rogobaseandroid-release.aar")
    if (aarFile.exists()) {
        from(zipTree(aarFile))
        include("classes.jar")
        into(extractedAarDir)
        rename("classes.jar", "rogobaseandroid-release-internal.jar")
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)

    // 1. Nhúng các file JAR bình thường
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // 2. FIXED: Nhúng file JAR đã giải nén
    // We pass the task provider directly to api() or use builtBy separately
    api(files(extractedAarDir.get().file("rogobaseandroid-release-internal.jar")) {
        builtBy(extractAarJarTask)
    })

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
                version = "1.0.3.5" // Tăng version để JitPack nhận bản mới nhất
            }
        }
    }
}