plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.seriazliation)
    id("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("com.google.devtools.ksp")
}

android {
    namespace  = "com.sirteefyapps.funquizes"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sirteefyapps.funquizes"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation (libs.ktor.client.core)
    implementation (libs.ktor.client.android)
    implementation (libs.ktor.serialization.kotlinx.json)
    implementation (libs.kotlinx.serialization.json)
    implementation (libs.ktor.client.content.negotiation)
    implementation (libs.ktor.client.logging)


    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)


    implementation (libs.ktor.client.cio)
    implementation (libs.ktor.client.serialization)
    // Ktor JSON Serialization
    // Kotlinx Serialization
    // View model dependency
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.hilt.navigation.compose)
    // Hilt Dagger
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Gson
    implementation(libs.gson)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
