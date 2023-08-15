import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias (libs.plugins.android.kotlin)
    alias (libs.plugins.android.navigationargs)
    id("kotlin-kapt")
    id ("themovie.android.hilt")
}
val localProperties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}
val baseUrl = localProperties["baseUrl"] ?: ""

android {
    namespace = "br.com.themoviedb"
    compileSdk =  33

    defaultConfig {
        applicationId = "br.com.themoviedb"
        minSdk = 24
        targetSdk =33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField ("String", "BASE_URL", "\"$baseUrl\"")
    }

    buildTypes {
        release {
        isMinifyEnabled = true
        proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
      }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation (libs.kotlin)
    implementation (libs.androidx.compact)
    implementation (libs.bundles.ui.util)
    implementation (libs.google.material)
    implementation (libs.constraint.layout)
    implementation (libs.bundles.coroutine)
    implementation (libs.bundles.viewmodel)
    implementation (libs.bundles.navigation)
    implementation (project(":core:network"))
    implementation (project(":features:movies"))
    implementation (project(":features:details_movie"))
    implementation (project(":features:favorites"))
    implementation (project(":core:ui_kit"))
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidTest)
}

kapt {
    correctErrorTypes = true
}