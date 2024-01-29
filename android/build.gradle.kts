import app.duss.kcommerce.Configuration
import app.duss.kcommerce.Deps
import app.duss.kcommerce.Versions

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "app.duss.kcommerce.android"
    compileSdk = Configuration.compileSdk
    defaultConfig {
        applicationId = "app.duss.kcommerce.android"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packaging {
        resources {
            pickFirsts.add(
                "META-INF/INDEX.LIST"
            )
            excludes.addAll(
                listOf(
                    "META-INF/AL2.0",
                    "META-INF/LGPL2.1",
                ),
            )
        }
    }
    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
        }

        getByName("debug") {
            isMinifyEnabled = false
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Deps.Androidx.Activity.activityCompose)

    // Koin
    with(Deps.Koin) {
        api(android)
    }
}