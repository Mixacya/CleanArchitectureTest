plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "com.mikhailapp.data"
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}