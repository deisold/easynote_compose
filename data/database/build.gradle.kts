plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "easynotecompose.data.network"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    api(project(":core"))

    api(libs.androidx.core.ktx)
    api(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}