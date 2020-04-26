plugins {
    libDefault()
    kotlin(Plugins.kapt)
    id(Plugins.serialization)
}

android {
    default()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Android.constraintLayout)
    implementation(Libraries.Android.androidXfragment)
    implementation(Libraries.Android.viewModel)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerAnnotations)
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.serialization)
    implementation(Libraries.indicator)
    implementation(project(Modules.core))
    implementation(project(Modules.commonUi))
    implementation(project(Modules.commonModels))
    testImplementation(Libraries.Testing.junit)
    androidTestImplementation(Libraries.Testing.androidXjunit)
    androidTestImplementation(Libraries.Testing.espresso)
}
