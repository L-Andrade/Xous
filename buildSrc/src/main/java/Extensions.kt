import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

fun BaseExtension.default() {
    compileSdkVersion(Config.compileSdk)

    defaultConfig {
        this@defaultConfig.default()
    }

    enableJava8()

    buildTypes {
        getByName(Config.release) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

private fun DefaultConfig.default() {
    minSdkVersion(Config.minSdk)
    targetSdkVersion(Config.targetSdk)
    versionCode = Config.Version.code
    versionName = Config.Version.name

    testInstrumentationRunner = Config.instrumentationRunner
}

private fun BaseExtension.enableJava8() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun PluginDependenciesSpec.libDefault() {
    id("com.android.library")
    defaultPlugins()
}

fun PluginDependenciesSpec.defaultPlugins() {
    kotlin("android")
    kotlin("android.extensions")
}