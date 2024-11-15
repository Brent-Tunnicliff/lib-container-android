// Copyright © 2024 Brent Tunnicliff <brent@tunnicliff.dev>

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "dev.tunnicliff.container"
    compileSdk = 35

    defaultConfig {
        minSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "dev.tunnicliff"
                artifactId = "lib-container-android"
                version = "1.0.0-beta.2"

                pom {
                    packaging = "aar"
                    name.set("lib-container-android")
                    description.set("lib-container-android: Library of helpers for using manual dependency injection container pattern.")
                    url.set("https://github.com/Brent-Tunnicliff/lib-container-android")
                    inceptionYear.set("2024")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("brent")
                            name.set("Brent Tunnicliff")
                            email.set("brent@tunnicliff.dev")
                        }
                    }

                    scm {
                        connection.set("scm:git:https://github.com/Brent-Tunnicliff/lib-container-android.git")
                        developerConnection.set("scm:git:ssh://git@github.com:Brent-Tunnicliff/lib-container-android.git")
                        url.set("https://github.com/Brent-Tunnicliff/lib-container-android")
                    }
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}