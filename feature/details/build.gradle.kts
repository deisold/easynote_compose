plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "easynotecompose.feature.details"
    compileSdk = libs.versions.compileSdk.get().toInt()

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(project(":core"))
    api(project(":core:ui"))
    api(project(":design"))

    api(libs.koin.android)
    api(libs.androidx.core.ktx)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.navigation.compose)
    api(libs.androidx.navigation.ui.ktx)
    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.viewModelCompose)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.util)

    api(libs.kotlinx.coroutines.android)

    api(libs.koin.android)
    api(libs.koin.android.compat)
    api(libs.koin.androidx.navigation)
    api(libs.koin.androidx.compose)

    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}