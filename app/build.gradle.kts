plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.tiagopereira.globotmdb"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.tiagopereira.globotmdb"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-ktx:1.7.2")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Constraintlayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // CardView
    implementation("androidx.cardview:cardview:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.google.code.gson:gson:2.9.1")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.0")

    // Coil
    implementation("io.coil-kt:coil:2.4.0")

    // PhotoView
    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    // Room
    val roomVersion = "2.5.2"
    implementation ("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomVersion")

    implementation("androidx.room:room-ktx:$roomVersion")

    // YouTube
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}