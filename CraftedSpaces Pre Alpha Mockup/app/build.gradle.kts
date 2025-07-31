plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.craftedspaces"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.craftedspaces"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false // ✅ Fix added here
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

    // Optional: Kotlin DSL for view binding (if you're using it)
    // buildFeatures {
    //     viewBinding = true
    // }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Location Services
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Material Components (latest)
    implementation("com.google.android.material:material:1.12.0")
}
