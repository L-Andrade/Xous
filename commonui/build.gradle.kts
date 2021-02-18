plugins {
    libDefault()
}

android {
    xous()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Android.appCompat)
    implementation(Libraries.Android.core)
    implementation(Libraries.Android.material)
    implementation(Libraries.glide)
    implementation(Libraries.coroutines)
    implementation(project(Modules.commonModels))
    api(Libraries.indicator)
}
