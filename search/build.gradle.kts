plugins {
    libDefault()
    kotlin(Plugins.kapt)
}

android {
    default()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Android.fragment)
    implementation(Libraries.Android.viewModel)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerAnnotations)
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.serialization)
    implementation(project(Modules.core))
    implementation(project(Modules.commonModels))
    implementation(project(Modules.commonUi))
}
