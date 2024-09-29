plugins {
    id("module.plugin")
}

android {
    namespace = "com.example.digitain_test_task.domain"
}

dependencies {
    //region Retrofit
    implementation(Libs.Retrofit.RETROFIT)
    implementation(Libs.Retrofit.COROUTINE_ADAPTER)
    //endregion
}