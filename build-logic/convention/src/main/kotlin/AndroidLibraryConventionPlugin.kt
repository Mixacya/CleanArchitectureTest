import com.android.build.gradle.LibraryExtension
import mikhailapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            pluginManager.apply("kotlin-parcelize")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig {
                    buildConfigField("Boolean", "IS_LOCAL", "false")
                }

                buildFeatures.apply {
                    buildConfig = true
                }

                buildTypes {
                    create("local") {
                        initWith(buildTypes["debug"])
                        buildConfigField("Boolean", "IS_LOCAL", "true")
                    }
                }
            }

            dependencies.apply {
                add("implementation", "androidx.core:core-ktx:1.9.0")
                add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
                add("implementation", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                add("implementation", project(":constants"))
            }
        }
    }
}