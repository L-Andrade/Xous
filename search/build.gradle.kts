plugins {
    libDefault()
    kotlin(Plugins.kapt)
    id(Plugins.serialization)
    id(Plugins.anvil) version Versions.anvil
}

android {
    xous()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Android.fragment)
    implementation(Libraries.Android.viewModel)
    implementation(Libraries.Android.constraintLayout)
    implementation(Libraries.dagger)
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.serialization)
    implementation(project(Modules.core))
    implementation(project(Modules.commonModels))
    implementation(project(Modules.commonUi))
}

anvil {
    generateDaggerFactories = true // default is false
}