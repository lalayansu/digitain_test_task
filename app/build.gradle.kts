plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.digitain_test_task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.digitain_test_task"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    flavorDimensions.add("type")
    productFlavors {
        create("digitain_test") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://pm.by/siteapi/Statistics/\""
            )
            dimension = "type"
            applicationIdSuffix = ".test"

            resValue("string", "app_name", "[Test]Digitain")
        }

        create("digitain_prod") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://pm.by/siteapi/Statistics/\""
            )
            dimension = "type"

            resValue("string", "app_name", "Digitain")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                "proguard-rules.pro"
            )
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {
    implementation(project(":presenter"))
    implementation(project(":domain"))
    implementation(project(":data"))

    //Kotlin
    implementation(Libs.Kotlin.STD_LIB)
    implementation(Libs.Kotlin.COROUTINES_CORE)
    implementation(Libs.Kotlin.COROUTINES_ANDROID)

    //AndroidX
    implementation(Libs.Android.APPCOMPAT)
    implementation(Libs.Android.CORE_KTX)
    implementation(Libs.Android.LIFECYCLE_VIEW_MODEL)
    implementation(Libs.Android.LIFECYCLE_RUNTIME)

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.KAPT)
    kapt(Libs.Hilt.ANDROIDX)
}
