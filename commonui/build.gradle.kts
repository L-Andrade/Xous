plugins {
    libDefault()
}

android {
    default()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Android.appCompat)
    implementation(Libraries.Android.core)
    implementation(Libraries.Android.material)
    api(Libraries.indicator)
}
