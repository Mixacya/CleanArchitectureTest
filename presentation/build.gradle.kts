plugins {
    id("convention.android.library")
    id("convention.android.compose")
    id("convention.android.hilt")
}

android {
    namespace = "com.mikhailapp.presentation"
}

dependencies {
    implementation(project(":domain"))
}
