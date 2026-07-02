plugins {
    id("com.android.library")
    alias(libs.plugins.androidBuiltinKotlin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.dirkeisold.easynotecompose.design"
    compileSdk = libs.versions.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    api(platform(libs.androidx.compose.bom))

    api(libs.androidx.core.ktx)
    api(libs.koin.android)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
