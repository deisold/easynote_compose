// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()

//        if (!libs.versions.compose.snapshot.get().endsWith("SNAPSHOT")) {
//            maven { url = uri("https://androidx.dev/snapshots/builds/${libs.versions.compose.snapshot.get()}/artifacts/repository/") }
//        }
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
    }
}


subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.43.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")


//plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.kotlin.jvm) apply false
//    alias(libs.plugins.kotlin.serialization) apply false
////    id 'com.android.application' version '8.0.0-alpha10' apply false
////    id 'com.android.library' version '8.0.0-alpha10' apply false
////    id 'org.jetbrains.kotlin.android' version '1.7.21' apply false
//}