import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


group = "br.com.themoviedb.build-logic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.gradle)
}

gradlePlugin {

    plugins {
        register("androidHilt") {
            id = "themovie.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}


tasks.test {
    useJUnitPlatform()
}