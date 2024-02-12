import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.kapt")
            pluginManager.apply("com.google.dagger.hilt.android")

            dependencies.apply {
                add("implementation", "com.google.dagger:hilt-android:2.44")
//                add("implementation", "com.google.dagger:hilt-android-compiler:2.44")
                add("implementation", "androidx.hilt:hilt-navigation-compose:1.0.0")
                add("kapt","com.google.dagger:hilt-android-compiler:2.44")
            }
        }
    }
}