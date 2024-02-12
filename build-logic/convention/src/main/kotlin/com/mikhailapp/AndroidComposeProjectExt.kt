package mikhailapp

import com.android.build.api.dsl.CommonExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
//            kotlinCompilerExtensionVersion = "1.5.7" //Here is newer version
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
            }
        }
    }

    dependencies {
        "implementation"("androidx.activity:activity-compose:1.8.1")
        "implementation"(platform("androidx.compose:compose-bom:2023.03.00"))
        "implementation"("androidx.compose.ui:ui")
        "implementation"("androidx.compose.ui:ui-graphics")
        "implementation"("androidx.compose.ui:ui-tooling-preview")
        "implementation"("androidx.compose.material3:material3")
        "debugImplementation"("androidx.compose.ui:ui-tooling")
        "debugImplementation"("androidx.compose.ui:ui-test-manifest")
        "implementation"("androidx.compose.runtime:runtime-livedata:1.5.4")
        "implementation"("androidx.navigation:navigation-compose:2.7.6")
        "implementation"("io.coil-kt:coil-compose:2.5.0")
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val relativePath = projectDir.relativeTo(rootDir)
    val buildDir = layout.buildDirectory.get().asFile
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = buildDir.resolve("compose-metrics").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath,
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = buildDir.resolve("compose-reports").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath,
        )
    }
    return metricParameters.toList()
}