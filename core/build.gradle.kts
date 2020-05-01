import java.util.*

plugins {
    libDefault()
    kotlin(Plugins.kapt)
    id(Plugins.safeArgs)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

android {
    default()

    buildTypes {
        getByName(Config.debug) {
            buildConfigField("String", "API_KEY", localProperties.getProperty("api_key"))
        }
        getByName(Config.release) {
            buildConfigField("String", "API_KEY", localProperties.getProperty("api_key"))
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    api(Libraries.Android.appCompat)
    api(Libraries.Android.core)
    api(Libraries.Android.material)
    api(Libraries.Android.navigation)
    api(Libraries.Android.navigationFragment)
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.serialization)
    implementation(Libraries.Network.serializationAdapter)
    implementation(Libraries.Network.okHttpInterceptor)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerAnnotations)
    implementation(Libraries.glide)
    implementation(project(Modules.db))
    // Unfortunately, :core needs to know about models too, to generate SafeArgs
    implementation(project(Modules.commonModels))
}
