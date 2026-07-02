plugins {
    id("com.android.library")
    alias(libs.plugins.androidBuiltinKotlin)
}

android {
    namespace = "easynotecompose.data.repository"
    compileSdk = libs.versions.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":data:database"))
    api(libs.androidx.core.ktx)

    api(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
