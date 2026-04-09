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

    // Dùng compileOnly để IDE nhận class từ .jar và .aar mà không bị lỗi khi build AAR
    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    
    // Nếu bạn muốn khi app khác dùng thư viện này cũng thấy các class đó, 
    // bạn phải cài đặt theo cách khác, nhưng để CODE ĐƯỢC trong hii.java thì dùng dòng trên.
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.xtung2404"
                artifactId = "RogoCoreLib"
                version = "1.0.2.2"
            }
        }
    }
}
