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
    implementation(Libraries.Android.androidXCore)
    implementation(Libraries.Android.material)
}
