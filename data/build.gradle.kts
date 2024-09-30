plugins {
    id("module.plugin")
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
}

android {
    namespace = "com.example.digitain_test_task.data"
}

dependencies {
    implementation(project(":domain"))

    //region Retrofit, Gson and OkHttp
    implementation(Libs.Retrofit.OK_HTTP)
    implementation(Libs.Retrofit.GSON)
    implementation(Libs.Retrofit.GSON_CONVERTER)
    implementation(Libs.Retrofit.RETROFIT)
    //endregion

    //region AndroidX lifecycle
    implementation(Libs.Android.LIFECYCLE_PROCESS)
    //endregion

    // region debugImplementation
    debugImplementation(Libs.Retrofit.HTTP_INTERCEPTOR)
    debugImplementation(Libs.Retrofit.COROUTINE_ADAPTER)
    //endregion
}