import com.android.build.api.dsl.ApplicationExtension
import mikhailapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                namespace = Constants.applicationId
                defaultConfig.apply {
                    targetSdk = Constants.targetSdkVersion
                    applicationId = Constants.applicationId
                    versionCode = Constants.versionCode
                    versionName = Constants.versionName
                }

                buildFeatures.apply {
                    buildConfig = true
                }
            }
        }
    }

}