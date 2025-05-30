plugins {
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("androidx.room")
    id("com.android.library")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.home"
    compileSdk = 35

    room {
        schemaDirectory("$projectDir/schemas")
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

kapt {
    correctErrorTypes = true
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
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.dagger)
    implementation(libs.androidx.room.ktx)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    annotationProcessor(libs.androidx.room.compiler.v260)
    androidTestImplementation(libs.androidx.espresso.core)


//    implementation(project(":wrapper"))

    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.recyclerview.selection)

    implementation(libs.cicerone)

    implementation(project(":core"))
}