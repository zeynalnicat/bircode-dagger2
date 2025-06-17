plugins {
    alias(libs.plugins.kotlin.android)
    id("com.android.library")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34
//
//    defaultConfig {
//        applicationId = "com.example.myapplication"
//        minSdk = 34
//        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    kapt(libs.dagger.compiler)
    implementation(libs.dagger)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.terrakok:cicerone:7.1")
    implementation("androidx.core:core-ktx:1.16.0")
}