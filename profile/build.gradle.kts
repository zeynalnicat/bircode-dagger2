plugins {
//    alias(libs.plugins.android.application)
    id("com.android.library")
    id("kotlin-kapt")
    alias(libs.plugins.kotlin.android)
    id("androidx.room")
}

android {
    namespace = "com.example.profile"
    compileSdk = 35

    room {
        schemaDirectory("$projectDir/schemas")
    }


//    defaultConfig {
//        applicationId = "com.example.home"
//        minSdk = 24
//        targetSdk = 33
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

    buildFeatures {
        viewBinding = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    kapt(libs.androidx.room.compiler)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler.v260)

    implementation(libs.glide)

    implementation("com.github.terrakok:cicerone:7.1")

}