plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "com.mikhailapp.domain"
}

dependencies {
    implementation(project(":data"))
}