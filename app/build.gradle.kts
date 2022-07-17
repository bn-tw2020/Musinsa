plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.homework.myapplication"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(Lib.AndroidX.CORE)
    implementation(Lib.AndroidX.APPCOMPAT)
    implementation(Lib.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Lib.AndroidX.FRAGMENT_KTX)
    implementation(Lib.AndroidX.ACTIVITY_KTX)
    implementation(Lib.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.AndroidX.RECYCLERVIEW)
    implementation(Lib.AndroidX.LIVEDATA)
    implementation(Lib.Material.MATERIAL)
    implementation(Lib.Test.JUNIT)
    implementation(Lib.Test.JUNIT_TEST)
    implementation(Lib.Test.ESPRESSO)
    implementation(Lib.Gson.GSON)
    implementation(Lib.Glide.GLIDE)
    kapt(Lib.Glide.COMPILER)
    implementation(Lib.Hilt.ANDROID)
    kapt(Lib.Hilt.COMPILER)
    implementation(Lib.Retrofit.RETROFIT2)
    implementation(Lib.Retrofit.RETROFIT2_CONVERTER_SCALARS)
    implementation(Lib.Retrofit.RETROFIT2_CONVERTER_MOSHI)
    implementation(Lib.OkHttp3.OKHTTP3)
    implementation(Lib.OkHttp3.OKHTTP3_LOGGING_INTERCEPTER)
    implementation(Lib.Moshi.MOSHI)
    implementation(Lib.COROUTINE.CORE)
    implementation(Lib.COROUTINE.ANDROID)
    implementation(Lib.COROUTINE.TEST)
}