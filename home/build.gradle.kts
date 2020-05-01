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
    implementation(Libraries.Android.fragment)
    implementation(Libraries.Android.lifecycleLiveData)
    implementation(Libraries.Android.viewModel)
    implementation(Libraries.dagger)
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.serialization)
    implementation(Libraries.flexbox)
    kapt(Libraries.daggerAnnotations)
    implementation(project(Modules.core))
    implementation(project(Modules.commonModels))
    implementation(project(Modules.db))
    testImplementation(Libraries.Testing.junit)
    androidTestImplementation(Libraries.Testing.androidXjunit)
    androidTestImplementation(Libraries.Testing.espresso)
}
