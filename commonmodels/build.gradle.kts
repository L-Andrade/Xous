plugins {
    libDefault()
    id(Plugins.serialization)
}

android {
    xous()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Network.serialization)
}
