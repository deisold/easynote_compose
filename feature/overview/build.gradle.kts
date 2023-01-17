plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "easynotecompose.feature.overview"
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

    implementation(project(":data:database"))
    implementation(libs.androidx.core.ktx)

    api(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}