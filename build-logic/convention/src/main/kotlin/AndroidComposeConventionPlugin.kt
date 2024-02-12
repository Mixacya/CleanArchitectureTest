import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import mikhailapp.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class AndroidComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            println(extensions.extensionsSchema.joinToString())
            extensions.findByType<ApplicationExtension>()?.let { extension ->
                configureAndroidCompose(extension)
            }
            extensions.findByType<LibraryExtension>()?.let { extension ->
                configureAndroidCompose(extension)
            }
        }
    }
}