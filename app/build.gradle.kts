plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}
android {
    namespace = "com.example.merlinproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.merlinproject"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1.1"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.coil.compose)

    // Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)

    // To use constraintlayout in compose
    implementation(libs.constraintlayout.compose)

    // Icons
    implementation(libs.material.icons.extended)

    // Window Size
    implementation("androidx.compose.material3:material3-window-size-class")

    // Firebase
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation(libs.firebase.firestore)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Retrofit
    implementation(libs.retrofit)
    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.dagger.hilt.compiler)
    // Hilt ViewModel
    implementation(libs.navigation.compose)
    // ViewModels
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v251)
    implementation(libs.coil.compose.v210)
    // Core KTX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    //material 3
    implementation(libs.androidx.material3.android)
}
kapt {
    correctErrorTypes = true
}